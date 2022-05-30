package dat.startcode.control;

import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.services.CarportCalculator;
import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DrawingCommand extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.getRole().equals("kunde")) {
            return "error";
        }

        //hent carport bredde
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));

        //hent carport længde
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));

        // hent carport højde
        int carportHeight = Integer.parseInt(request.getParameter("carportHeight"));
        String shedChosen = request.getParameter("shedChosen");

        int shedLength = 0;
        if (request.getParameter("shedLength") != null) {
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
        }
        //tagmateriale
        String roofType = request.getParameter("roofMaterial");
        //placering
        String shedPlacement = request.getParameter("shedPlacement");
        //skurSize
        int shedSize = Integer.parseInt(request.getParameter("shedSize"));

        if (shedChosen != null) {
            carportLength += Integer.parseInt(request.getParameter("shedLength"));
            //redskabsrum bredde
            int shedWidth = (carportWidth / 2) - 35;
            session.setAttribute("shedWidth", shedWidth);
        }

        int antalSpær = (int) Math.ceil(carportLength / 59.0);
        double spærAfstand = (carportLength + 0.0) / antalSpær;

        /*VI TEGNER SVG I DET FØLGENDE*/
        SVG svg = new SVG(0, 0, "0 0 1200 800", 100, 100);
        SVG innerSVG = new SVG(50, 50, "0 0 1200 800", 100, 100);

        //Tegn Carport parkerings del
        innerSVG.addRect(0, 0, carportWidth, carportLength + 20);

        //Tegn remme
        //øverste rem
        innerSVG.addRect(0, 35, 5, carportLength + 20);
        //nederste rem
        innerSVG.addRect(0, carportWidth - 35, 5, carportLength + 20);

        //Skur tilvalgt
        if (shedChosen != null) {
            //Tegn spær
            int spærStartX = 0;
            int totalAntalSpær = 1;
            int stolpeXStart = 0;
            int stolpeStartMidt = 0;

            while (spærStartX < carportLength / 2) {
                innerSVG.addRect(spærStartX, 0, carportWidth, 5);
                totalAntalSpær++;
                spærStartX += spærAfstand;
                stolpeStartMidt = spærStartX;
                if (totalAntalSpær == 2) stolpeXStart = spærStartX - 3;
            }

            spærStartX = carportLength - 5;
            while (spærStartX > (carportLength / 2) - 7) {
                totalAntalSpær++;
                innerSVG.addRect(spærStartX, 0, carportWidth, 5);
                spærStartX -= spærAfstand;
            }

            //Tegn stolper
            int stolpeYStart = 32;

            //Tegn skur
            if (shedPlacement.equals("center")) {
                innerSVG.addShedRectTemplate(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, stolpeYStart, carportWidth - 59, (int) Math.round(shedSize * spærAfstand) + 11);
            }

            if (shedPlacement.equals("right")) {
                innerSVG.addShedRectTemplate(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, carportWidth / 2, (carportWidth / 2) - 33, (int) Math.round(shedSize * spærAfstand) + 11);
            }

            if (shedPlacement.equals("left")) {
                innerSVG.addShedRectTemplate(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, stolpeYStart, (carportWidth / 2) - 19, (int) Math.round(shedSize * spærAfstand) + 11);
            }

            //2 forreste stolper
            innerSVG.addRect(stolpeXStart, stolpeYStart, 11, 11);
            innerSVG.addRect(stolpeXStart, carportWidth - 38, 11, 11);

            //2 bagerste stolper
            innerSVG.addRect(carportLength - 8, stolpeYStart, 11, 11);
            innerSVG.addRect(carportLength - 8, carportWidth - 38, 11, 11);

            //Stolper ved skur start
            innerSVG.addRect(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, stolpeYStart, 11, 11);
            innerSVG.addRect(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, carportWidth - 38, 11, 11);

            //Stolper midt mellem skur start og carport start
            innerSVG.addRect((carportLength - (int) Math.round(shedSize * spærAfstand) - 8 + stolpeXStart) / 2, stolpeYStart, 11, 11);
            innerSVG.addRect((carportLength - (int) Math.round(shedSize * spærAfstand) - 8 + stolpeXStart) / 2, carportWidth - 38, 11, 11);

            //Evt midterstolper skur
            if (carportWidth >= 380) {
                //Bagerste
                innerSVG.addRect(carportLength - 8, carportWidth / 2, 11, 11);
                //Forreste
                innerSVG.addRect(carportLength - (int) Math.round(shedSize * spærAfstand) - 8, carportWidth / 2, 11, 11);
            }

            //Stolpe ved skurdør
            if (shedPlacement.equals("center")) {
                innerSVG.addRect(carportLength - 8 - 2 * (int) Math.round(spærAfstand), carportWidth - 38, 11, 11);
            }
            if (shedPlacement.equals("left") || shedPlacement.equals("right")) {
                innerSVG.addRect(carportLength - 8 - 2 * (int) Math.round(spærAfstand), carportWidth / 2, 11, 11);
            }

            //Evt supplerende stolper
            if (shedSize * spærAfstand > 310 && shedSize * spærAfstand < 310 + 2 * spærAfstand) {
                int halfShed = ((carportLength - (int) Math.round(shedSize * spærAfstand) - 8) + (carportLength - 8)) / 2;
                innerSVG.addRect(halfShed, stolpeYStart, 11, 11);
            }
            if (shedSize * spærAfstand > 310 + 2 * spærAfstand) {
                int halfShed = ((carportLength - (int) Math.round(shedSize * spærAfstand) - 8) + (carportLength - 8)) / 2;
                innerSVG.addRect(halfShed, stolpeYStart, 11, 11);
                int shedStartX = carportLength - (int) Math.round(shedSize * spærAfstand) - 8;
                int dørStolpe = carportLength - 8 - 2 * (int) Math.round(spærAfstand);
                int halvDørside = (shedStartX + dørStolpe) / 2;
                innerSVG.addRect(halvDørside, carportWidth - 38, 11, 11);
            }

            //Tilføjer døren
            if (shedPlacement.equals("center")) {
                innerSVG.addShedLine(carportLength - 8, carportWidth - 33, carportLength - 8 - 100, carportWidth - 13);
            }

            if (shedPlacement.equals("left")) {
                innerSVG.addShedLine(carportLength - 8, carportWidth / 2+11, carportLength - 8 - 100, carportWidth / 2 + 24);
            }
            if (shedPlacement.equals("right")) {
                innerSVG.addShedLine(carportLength - 8, carportWidth / 2, carportLength - 8 - 100, carportWidth / 2 - 13);
            }
            innerSVG.addCrossLine(stolpeXStart + 5, stolpeYStart + 5, carportLength - (int) Math.round(shedSize * spærAfstand) - 8, carportWidth - 33);
            innerSVG.addCrossLine(stolpeXStart + 5, carportWidth - 33, carportLength - (int) Math.round(shedSize * spærAfstand) - 8, stolpeYStart + 5);
        } else {
            //Skur fravalgt
            //Tegn spær
            int spærStartX = 0;
            int totalAntalSpær = 1;
            int stolpeXStart = 0;
            int stolpeStartMidt = 0;

            while (spærStartX < carportLength / 2) {
                innerSVG.addRect(spærStartX, 0, carportWidth, 5);
                totalAntalSpær++;
                spærStartX += spærAfstand;
                stolpeStartMidt = spærStartX;
                if (totalAntalSpær == 2) stolpeXStart = spærStartX - 3;
            }

            spærStartX = carportLength - 5;
            while (spærStartX > (carportLength / 2) - 7) {
                totalAntalSpær++;
                innerSVG.addRect(spærStartX, 0, carportWidth, 5);
                spærStartX -= spærAfstand;
            }

            //Tegn stolper
            int stolpeYStart = 32;

            //2 forreste stolper
            innerSVG.addRect(stolpeXStart, stolpeYStart, 11, 11);
            innerSVG.addRect(stolpeXStart, carportWidth - 38, 11, 11);

            //2 bagerste stolper
            innerSVG.addRect(carportLength - 8, stolpeYStart, 11, 11);
            innerSVG.addRect(carportLength - 8, carportWidth - 38, 11, 11);

            //Midterste 2 stolper
            if (totalAntalSpær % 2 != 0) {
                int correctionFactor = 0;
                if (carportLength == 480) correctionFactor = 9;
                if (carportLength == 500) correctionFactor = 7;
                if (carportLength == 520) correctionFactor = 6;
                if (carportLength == 600) correctionFactor = 7;

                innerSVG.addRect(stolpeStartMidt - correctionFactor, stolpeYStart, 11, 11);
                innerSVG.addRect(stolpeStartMidt - correctionFactor, carportWidth - 38, 11, 11);
            } else {
                innerSVG.addRect((carportLength + (int) Math.round(spærAfstand)) / 2 - 7, stolpeYStart, 11, 11);
                innerSVG.addRect((carportLength + (int) Math.round(spærAfstand)) / 2 - 7, carportWidth - 38, 11, 11);
            }
            innerSVG.addCrossLine(stolpeXStart + 5, stolpeYStart + 5, carportLength - 3, carportWidth - 33);
            innerSVG.addCrossLine(stolpeXStart + 5, carportWidth - 33, carportLength - 3, stolpeYStart + 5);
        }

        //Afstandsmålere
        //nederste linje
        svg.addLine(carportLength + 70, carportWidth + 100, 50, carportWidth + 100);
        svg.addLine(carportLength + 70, carportWidth + 90, carportLength + 70, carportWidth + 110);
        svg.addLine(50, carportWidth + 90, 50, carportWidth + 110);
        svg.addLineTextX((carportLength + 120) / 2, carportWidth + 90, String.valueOf(carportLength));

        //venstre linje
        svg.addLine(10, 50, 10, carportWidth + 50);
        svg.addLine(0, 50, 20, 50);
        svg.addLine(0, carportWidth + 50, 20, carportWidth + 50);
        svg.addLineTextY(32, (carportWidth + 100) / 2, String.valueOf(carportWidth));

        svg.addSvg(innerSVG);

        int fullCarportShedLength = carportLength - shedLength;
        if (shedChosen != null) {
            carportLength -= shedLength;
            fullCarportShedLength += shedLength;
        }

        if (shedChosen == null) {
            shedChosen = "n";
        }

        List<ProductDTO> pDTO = (List<ProductDTO>) session.getAttribute("productDTOList");
        CarportCalculator calculator = new CarportCalculator(pDTO);
        try {
            calculator.calculateCarport(fullCarportShedLength,carportWidth,carportHeight,shedChosen,roofType,shedPlacement,shedSize);
        } catch (IllegalDimensionException e) {
            e.printStackTrace();
        }

        double carportPrice = ((double) Math.round(((calculator.getCarportPrice() *1.4)*100))/100);

        session.setAttribute("carportPrice", carportPrice);
        session.setAttribute("svgDrawing", svg.toString());
        session.setAttribute("carportWidth", carportWidth);
        session.setAttribute("carportLength", carportLength);
        session.setAttribute("carportHeight", carportHeight);
        session.setAttribute("roofType",roofType);
        session.setAttribute("shedChosen", shedChosen);
        session.setAttribute("shedPlacement", shedPlacement);
        session.setAttribute("shedLength", shedLength);
        if (shedChosen.equals("y")) {
            if (!shedPlacement.equals("center")) {
                session.setAttribute("shedWidth",(carportWidth/2)-35);
            } else {
                session.setAttribute("shedWidth", carportWidth-70);
            }
        }

        return "drawing";
    }
}