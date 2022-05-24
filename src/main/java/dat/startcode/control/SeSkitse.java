package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.services.CarportCalculator;
import dat.startcode.model.services.CustomerFacade;
import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SeSkitse extends Command {

    private ConnectionPool connectionPool;

    public SeSkitse()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        int carportId = Integer.parseInt(request.getParameter("getcarportid"));
        System.out.println(carportId);

        //getCarportById
        Carport carport = CustomerFacade.getCarportById(carportId, connectionPool);

        //hent carport bredde
        int carportWidth = carport.getCarportWidth();

        //hent carport længde
        int carportLength = carport.getCarportLength();

        // hent carport højde
        int carportHojde = carport.getCarportHeight();

        //String erRedskabsRumValgt = request.getParameter("redskabsrumValgt");
        boolean erRedskabsRumValgt = carport.isHasShed();

        String placering = null;
        int skurSize = 0;

        if(erRedskabsRumValgt) {
            int shedLength = carport.getShed().getLength();
            carportLength += shedLength;
            placering = carport.getShed().getPlacement();

            int antalSpær = (int) Math.ceil(carportLength/59.0);
            int spærAfstand = (int) Math.ceil(carportLength/antalSpær);
            skurSize = (int) Math.ceil(shedLength/spærAfstand);
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
        if (erRedskabsRumValgt) {

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
            if (placering!=null && placering.equals("midt")) {
                // innerSVG.addRect(carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart,carportWidth-59,(int)Math.round(skurSize*spærAfs)+11);
                innerSVG.addShedRectTemplate(carportLength-(int)Math.round(skurSize*spærAfs)-8,stolpeYStart,carportWidth-59,(int)Math.round(skurSize*spærAfs)+11);
            }

            if (placering!=null && placering.equals("højre")) {
                innerSVG.addShedRectTemplate(carportLength-(int)Math.round(skurSize*spærAfs)-8,carportWidth/2,(carportWidth/2)-33,(int)Math.round(skurSize*spærAfs)+11);
            }

            if (placering!= null && placering.equals("venstre")) {
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

        session.setAttribute("svgdrawing", svg.toString());

        return "seskitse";
    }


}
