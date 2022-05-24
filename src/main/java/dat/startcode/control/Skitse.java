package dat.startcode.control;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.services.CarportCalculator;
import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Skitse extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        //hent carport bredde
        int carportWidth = Integer.parseInt(request.getParameter("carportbredde"));

        //hent carport længde
        int carportLength = Integer.parseInt(request.getParameter("carportlængde"));

        // hent carport højde
        int carportHojde = Integer.parseInt(request.getParameter("carporthøjde"));
        String erRedskabsRumValgt = request.getParameter("redskabsrumValgt");

        int shedLength = 0;
        if(request.getParameter("redskabsrumLængde") != null) {
            shedLength = Integer.parseInt(request.getParameter("redskabsrumLængde"));
        }
        //tagmateriale
        String tagType = request.getParameter("tagtype");
        //placering
        String placering = request.getParameter("redskabsrumPlacering");
        //skurSize
        int skurSize = Integer.parseInt(request.getParameter("skurSize"));

        if(erRedskabsRumValgt != null) {
            carportLength += Integer.parseInt(request.getParameter("redskabsrumLængde"));
            //redskabsrum bredde
            int shedWidth = (carportWidth/2)-35;
            session.setAttribute("redskabsrumBredde", shedWidth);
        }

        int antalSpær = (int) Math.ceil(carportLength/59.0);
        double spærAfs = (carportLength + 0.0)/antalSpær;

        /*VI TEGNER SVG I DET FØLGENDE*/
        SVG svg = new SVG(0, 0, "0 0 1200 800", 100, 100);
        SVG innerSVG = new SVG(50,50,"0 0 1200 800",100,100);

        //Tegn Carport parkerings del
        innerSVG.addRect(0,0,carportWidth, carportLength+20);

        //Tegn remme
        //øverste rem
        innerSVG.addRect(0, 35, 5, carportLength+20);
        //nederste rem
        innerSVG.addRect(0, carportWidth-35, 5, carportLength+20);

        //Skur tilvalgt
        if (erRedskabsRumValgt != null) {

            //Tegn spær
            int spærStartX = 0;
            int numOfSpær = 1;
            int stolpeXStart = 0;
            int stolpeStartMidt = 0;

            while(spærStartX < carportLength/2) {
                innerSVG.addRect( spærStartX, 0,carportWidth, 5);
                numOfSpær++;
                spærStartX+=spærAfs;
                stolpeStartMidt = spærStartX;
                if (numOfSpær == 2) stolpeXStart = spærStartX-3;
            }

            spærStartX = carportLength - 5;
            while(spærStartX > (carportLength/2)-7) {
                numOfSpær++;
                innerSVG.addRect( spærStartX, 0,carportWidth, 5);
                spærStartX-=spærAfs;
            }

            //Tegn stolper
            int stolpeYStart = 32;

            //Tegn skur
            if (placering.equals("midt")) {
                // innerSVG.addRect(carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart,carportWidth-59,(int)Math.round(skurSize*spærAfs)+11);
                innerSVG.addShedRectTemplate(carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart,carportWidth-59,(int)Math.round(skurSize*spærAfs)+11);
            }

            if (placering.equals("højre")) {
                innerSVG.addShedRectTemplate(carportLength-(int)Math.round(skurSize*spærAfs)-8,carportWidth/2,(carportWidth/2)-33,(int)Math.round(skurSize*spærAfs)+11);

            }

            if (placering.equals("venstre")) {
                innerSVG.addShedRectTemplate(carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart,(carportWidth/2)-19,(int)Math.round(skurSize*spærAfs)+11);
            }

            //2 forreste stolper
            innerSVG.addRect(stolpeXStart, stolpeYStart, 11,11);
            innerSVG.addRect(stolpeXStart, carportWidth-38, 11,11);

            //2 bagerste stolper
            innerSVG.addRect(carportLength-8, stolpeYStart, 11,11);
            innerSVG.addRect(carportLength-8, carportWidth-38, 11,11);

            //Stolper ved skur start
            innerSVG.addRect(carportLength-(int)Math.round(skurSize*spærAfs)-8, stolpeYStart, 11,11);
            innerSVG.addRect(carportLength-(int)Math.round(skurSize*spærAfs)-8, carportWidth-38, 11,11);

            //Stolper midt mellem skur start og carport start
            innerSVG.addRect((carportLength-(int)Math.round(skurSize*spærAfs)-8+stolpeXStart)/2, stolpeYStart, 11,11);
            innerSVG.addRect((carportLength-(int)Math.round(skurSize*spærAfs)-8+stolpeXStart)/2, carportWidth-38, 11,11);

            //Evt midterstolper skur
            if (carportWidth>=380) {
                //Bagerste
                innerSVG.addRect(carportLength-8, carportWidth/2, 11,11);
                //Forreste
                innerSVG.addRect(carportLength-(int)Math.round(skurSize*spærAfs)-8, carportWidth/2, 11,11);
            }

            //Stolpe ved skurdør
            if (placering.equals("midt")) {
                innerSVG.addRect(carportLength-8-2*(int) Math.round(spærAfs), carportWidth-38, 11,11);
            }
            if (placering.equals("venstre") || placering.equals("højre")) {
                innerSVG.addRect(carportLength-8-2*(int) Math.round(spærAfs), carportWidth/2, 11,11);
            }

            //Evt suplerende stolper
            if (skurSize*spærAfs>310 && skurSize*spærAfs<310+2*spærAfs) {
                    int halvSkur = ((carportLength-(int)Math.round(skurSize*spærAfs)-8)+(carportLength-8))/2;
                    innerSVG.addRect(halvSkur, stolpeYStart, 11,11);
            }
            if (skurSize*spærAfs>310+2*spærAfs) {
                int halvSkur = ((carportLength-(int)Math.round(skurSize*spærAfs)-8)+(carportLength-8))/2;
                innerSVG.addRect(halvSkur, stolpeYStart, 11,11);
                int skurStartX = carportLength-(int)Math.round(skurSize*spærAfs)-8;
                int dørStolpe = carportLength-8-2*(int) Math.round(spærAfs);
                int halvtDørside = ( skurStartX+dørStolpe)/2;
                innerSVG.addRect(halvtDørside, carportWidth-38, 11,11);
            }

            int dørLængde = (int) Math.round(2*spærAfs);
            //Tilføjer døren
            if (placering.equals("midt")) {

                innerSVG.addShedLine(carportLength-8,carportWidth-33,carportLength-8-100,carportWidth-13);
            }
            if (placering.equals("venstre")) {

                innerSVG.addShedLine(carportLength-8,carportWidth/2,carportLength-8-100,carportWidth/2+13);

            }
            if (placering.equals("højre")) {
                innerSVG.addShedLine(carportLength-8,carportWidth/2,carportLength-8-100,carportWidth/2-13);

            }

            innerSVG.addCrossLine(stolpeXStart+5, stolpeYStart+5,carportLength-(int)Math.round(skurSize*spærAfs)-8,carportWidth-33);
            innerSVG.addCrossLine(stolpeXStart+5, carportWidth-33,carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart+5);

        } else {
            //Skur fravalgt
            //Tegn spær
            int spærStartX = 0;
            int numOfSpær = 1;
            int stolpeXStart = 0;
            int stolpeStartMidt = 0;

            while(spærStartX < carportLength/2) {
                innerSVG.addRect( spærStartX, 0,carportWidth, 5);
                numOfSpær++;
                spærStartX+=spærAfs;
                stolpeStartMidt = spærStartX;
                if (numOfSpær == 2) stolpeXStart = spærStartX-3;
            }

            spærStartX = carportLength - 5;
            while(spærStartX > (carportLength/2)-7) {
                numOfSpær++;
                innerSVG.addRect( spærStartX, 0,carportWidth, 5);
                spærStartX-=spærAfs;
            }

            //Tegn stolper
            int stolpeYStart = 32;

            //2 forreste stolper
            innerSVG.addRect(stolpeXStart, stolpeYStart, 11,11);
            innerSVG.addRect(stolpeXStart, carportWidth-38, 11,11);

            //2 bagerste stolper
            innerSVG.addRect(carportLength-8, stolpeYStart, 11,11);
            innerSVG.addRect(carportLength-8, carportWidth-38, 11,11);

            //Midterste 2 stolper
            if (numOfSpær%2 != 0) {

                int korrektion = 0;
                if (carportLength == 480) korrektion = 9;
                if (carportLength == 500) korrektion = 7;
                if (carportLength == 520) korrektion = 6;
                if (carportLength == 600) korrektion = 7;

                innerSVG.addRect(stolpeStartMidt-korrektion, stolpeYStart, 11,11);
                innerSVG.addRect(stolpeStartMidt-korrektion, carportWidth-38, 11,11);

            } else {
                innerSVG.addRect((carportLength+(int)Math.round(spærAfs))/2-7, stolpeYStart, 11,11);
                innerSVG.addRect((carportLength+(int)Math.round(spærAfs))/2-7, carportWidth-38, 11,11);
            }

            innerSVG.addCrossLine(stolpeXStart+5, stolpeYStart+5,carportLength-3,carportWidth-33);
            innerSVG.addCrossLine(stolpeXStart+5, carportWidth-33,carportLength-3,stolpeYStart+5);
        }

        //Afstandsmålere
        //nederste linje
        svg.addLine(carportLength+70,carportWidth+100,50,carportWidth+100);
        svg.addLine(carportLength+70,carportWidth+90,carportLength+70,carportWidth+110);
        svg.addLine(50,carportWidth+90,50,carportWidth+110);
        svg.addLineTextX((carportLength+120)/2,carportWidth+90, String.valueOf(carportLength));


        //venstre linje
        svg.addLine(10,50,10,carportWidth+50);
        svg.addLine(0,50,20,50);
        svg.addLine(0,carportWidth+50,20,carportWidth+50);
        svg.addLineTextY(32,(carportWidth+100)/2, String.valueOf(carportWidth));

        svg.addSvg(innerSVG);



        int fullCarportShedLength = carportLength - shedLength;
        if(erRedskabsRumValgt != null) {
            carportLength -= shedLength;
            fullCarportShedLength += shedLength;
        }

        System.out.println(fullCarportShedLength);
        System.out.println(carportWidth);
        System.out.println(carportHojde);
        System.out.println(erRedskabsRumValgt);
        System.out.println(placering);
        System.out.println(shedLength);
        System.out.println(skurSize);

        List<ProduktDTO> pDTO = (List<ProduktDTO>) session.getAttribute("productDTOListe");
        CarportCalculator calculator = new CarportCalculator(pDTO);
        try {
            calculator.beregnCarport(fullCarportShedLength,carportWidth,carportHojde,erRedskabsRumValgt,tagType,placering,skurSize);
        } catch (IllegalDimensionException e) {
            e.printStackTrace();
        }
        //calculator.beregnCarportPris();
        double carportPrice = ((double) Math.round(((calculator.carportPris*1.4)*100))/100);
        System.out.println(calculator.mList);

        session.setAttribute("carportPrice", carportPrice);

        session.setAttribute("svgdrawing", svg.toString());
        session.setAttribute("carportbredde", carportWidth);
        session.setAttribute("carportlængde", carportLength);
        session.setAttribute("carporthøjde", carportHojde);
        session.setAttribute("redskabsrumValgt", erRedskabsRumValgt);
        session.setAttribute("redskabsrumPlacering", placering);
        session.setAttribute("redskabsrumLængde", shedLength);

        return "skitse";
    }



}
