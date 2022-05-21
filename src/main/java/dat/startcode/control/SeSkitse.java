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


        int carportId = Integer.parseInt(request.getParameter("carportId"));

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


        //placering
        String placering = carport.getShed().getPlacement();

        int skurBredde = carport.getShed().getWidth();

        int skurLaengde = carport.getShed().getLength();



        /*VI TEGNER SVG I DET FØLGENDE*/
        SVG svg = new SVG(0, 0, "0 0 1200 800", 100, 50);
        int xStart = (1200-carportLength)/2;
        int yStart = (800-carportWidth)/2;

        //Her er redskabsrummet valgt
        if(erRedskabsRumValgt) {

            //redskabsrum bredde
            int shedWidth = (carportWidth/2)-35;
            session.setAttribute("redskabsrumBredde", shedWidth);

            //redskabsrum længde
            int shedLength = Integer.parseInt(request.getParameter("redskabsrumLaengde"));
            session.setAttribute("redskabsrumLængde", shedLength);

            int fullLength = shedLength + carportLength;

            int antalSpær = (int) Math.ceil(fullLength/59.0);
            int spærAfstand = (int) Math.ceil(fullLength/antalSpær);
            //udregn stolper, rem og spær MED redskabsrum og tegn svg

            //hvis redskabsrummets placering er: venstre
            if(placering.equals("venstre")) {
                //tegn carport ydre del

                svg.addRect(xStart,yStart,carportWidth, fullLength);

                //tegn kryds
                svg.addLine(xStart+spærAfstand, yStart+35,xStart+carportLength-10,yStart+carportWidth-30);
                svg.addLine(xStart+spærAfstand, yStart+carportWidth-30, xStart+carportLength-10, yStart+35);

                //tegn rem
                //øverste/venstre rem
                svg.addRect(xStart, yStart+35, 5, fullLength-5);

                //nederste/højre rem
                svg.addRect(xStart, yStart+carportWidth-35, 5, fullLength-5);

                //tegn spær
                int x = 0;
                int spærStartX = xStart;
                int spærStartY = yStart;
                int start = xStart;
                while(spærStartX < fullLength + start) {
                    svg.addRect(spærStartX + spærAfstand * x, spærStartY,carportWidth, 5);
                    spærStartX+=spærAfstand;
                }

                svg.addRect(xStart+fullLength-5, yStart, carportWidth,5);

                //tegn stolper
                //stolper øverste del
                svg.addRect(xStart+spærAfstand-3, yStart+32, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+32, 11,11); //bagerste stolpe

                //stolper den midterste del
                svg.addRect(xStart+fullLength-11,(carportWidth/2)+yStart,11,11); //bagerste stolpe
                svg.addRect(xStart+fullLength-19-shedLength, (carportWidth/2)+yStart,11,11); //midter stolpe
                svg.addRect(xStart+fullLength-19-shedLength,yStart+32,11,11); //øverste stolpe fremme
                svg.addRect(xStart+fullLength-19-shedLength,yStart+carportWidth-38,11,11); //nederste stolpe fremme
                svg.addRect(xStart+fullLength-10-(2*spærAfstand),(carportWidth/2)+yStart,11,11); //den hvor døren hægtes på
                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+32, 11,11); //midterste stolpe foroven før redskabsrum
                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+carportWidth-38, 11,11); //midterste stolpe forneden før redskabsrum



                //spær nedre del
                svg.addRect(xStart+spærAfstand-3, yStart+carportWidth-38, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+carportWidth-38, 11,11); //bagerste

                //streger om redskabsrum
                svg.addLine(xStart+carportLength-10, yStart+37,xStart+fullLength-11, yStart+37); //venstre side set forfra
                svg.addLine(xStart+carportLength-10,(carportWidth/2)+yStart+5, xStart+fullLength-11,(carportWidth/2)+yStart+5); //højre side set forfra
                svg.addLine(xStart+carportLength-10, yStart+37,xStart+carportLength-10,(carportWidth/2)+yStart+5); //forrest
                svg.addLine(xStart+fullLength-11, yStart+37, xStart+fullLength-11,(carportWidth/2)+yStart+5); //bagerst
                svg.addLine(xStart+fullLength-10-(2*spærAfstand),(carportWidth/2)+yStart+15, xStart+fullLength-11,(carportWidth/2)+yStart+5);//dør
            }


            //hvis redskabsrummets placering er: højre
            if(placering.equals("højre")) {
                //tegn carport ydre del

                svg.addRect(xStart,yStart,carportWidth, fullLength);

                //tegn kryds
                svg.addLine(xStart+spærAfstand, yStart+35,xStart+carportLength-10,yStart+carportWidth-30);
                svg.addLine(xStart+spærAfstand, yStart+carportWidth-30, xStart+carportLength-10, yStart+35);

                //tegn kryds
                /*svg.addLine(xStart+5+spærAfstand, yStart+35,xStart+carportLength-5,yStart+carportWidth-30);
                svg.addLine(xStart+5+spærAfstand, yStart+carportWidth-30, xStart+carportLength-5, yStart+35);*/

                //tegn rem
                //øverste/venstre rem
                svg.addRect(xStart, yStart+35, 5, fullLength-5);

                //nederste/højre rem
                svg.addRect(xStart, yStart+carportWidth-35, 5, fullLength-5);

                //tegn spær
                int x = 0;
                int spærStartX = xStart;
                int spærStartY = yStart;
                int start = xStart;
                while(spærStartX < fullLength + start) {
                    svg.addRect(spærStartX + spærAfstand * x, spærStartY,carportWidth, 5);
                    spærStartX+=spærAfstand;
                }

                svg.addRect(xStart+fullLength-5, yStart, carportWidth,5);

                //tegn stolper
                //stolper øverste del
                svg.addRect(xStart+spærAfstand-3, yStart+32, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+32, 11,11); //bagerste stolpe

                //stolper den midterste del
                svg.addRect(xStart+fullLength-11,(carportWidth/2)+yStart,11,11); //bagerste stolpe
                svg.addRect(xStart+fullLength-19-shedLength, (carportWidth/2)+yStart,11,11); //midter stolpe
                svg.addRect(xStart+fullLength-19-shedLength,yStart+32,11,11); //øverste stolpe fremme
                svg.addRect(xStart+fullLength-19-shedLength,yStart+carportWidth-38,11,11); //nederste stolpe fremme
                svg.addRect(xStart+fullLength-10-(2*spærAfstand),(carportWidth/2)+yStart,11,11); //den hvor døren hægtes på

                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+32, 11,11); //midterste stolpe foroven før redskabsrum
                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+carportWidth-38, 11,11); //midterste stolpe forneden før redskabsrum

                //spær nedre del
                svg.addRect(xStart+spærAfstand-3, yStart+carportWidth-38, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+carportWidth-38, 11,11); //bagerste

                //streger om redskabsrum
                svg.addLine(xStart+carportLength-11,(carportWidth/2)+yStart+5, xStart+fullLength-11,(carportWidth/2)+yStart+5); //venstre side set forfra
                svg.addLine(xStart+carportLength-11,yStart+carportWidth-35,xStart+fullLength-11, yStart+carportWidth-35); //højre side set forfra
                svg.addLine(xStart+carportLength-11,(carportWidth/2)+yStart+5,xStart+carportLength-11,yStart+carportWidth-35); //forrest
                svg.addLine(xStart+fullLength-11,(carportWidth/2)+yStart, xStart+fullLength-11, yStart+carportWidth-35); //bagerst
                svg.addLine(xStart+fullLength-10-(2*spærAfstand),(carportWidth/2)+yStart-15, xStart+fullLength-11,(carportWidth/2)+yStart+5);//dør

                //append mål til koordinat??
            }

            //hvis redskabsrummets placering er: midt
            if(placering.equals("midt")) {
                //tegn carport ydre del

                svg.addRect(xStart,yStart,carportWidth, fullLength);

                //tegn kryds
                svg.addLine(xStart+spærAfstand, yStart+35,xStart+carportLength-10,yStart+carportWidth-30);
                svg.addLine(xStart+spærAfstand, yStart+carportWidth-30, xStart+carportLength-10, yStart+35);

                //tegn kryds
                /*svg.addLine(xStart+5+spærAfstand, yStart+35,xStart+carportLength-5,yStart+carportWidth-30);
                svg.addLine(xStart+5+spærAfstand, yStart+carportWidth-30, xStart+carportLength-5, yStart+35);*/

                //tegn rem
                //øverste/venstre rem
                svg.addRect(xStart, yStart+35, 5, fullLength-5);

                //nederste/højre rem
                svg.addRect(xStart, yStart+carportWidth-35, 5, fullLength-5);

                //tegn spær
                int x = 0;
                int spærStartX = xStart;
                int spærStartY = yStart;
                int start = xStart;
                while(spærStartX < fullLength + start) {
                    svg.addRect(spærStartX + spærAfstand * x, spærStartY,carportWidth, 5);
                    spærStartX+=spærAfstand;
                }

                svg.addRect(xStart+fullLength-5, yStart, carportWidth,5);

                //tegn stolper
                //stolper øverste del
                svg.addRect(xStart+spærAfstand-3, yStart+32, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+32, 11,11); //bagerste stolpe

                //stolper den midterste del
                svg.addRect(xStart+fullLength-11,(carportWidth/2)+yStart,11,11); //bagerste stolpe
                svg.addRect(xStart+fullLength-19-shedLength, (carportWidth/2)+yStart,11,11); //midter stolpe
                svg.addRect(xStart+fullLength-19-shedLength,yStart+32,11,11); //øverste stolpe fremme
                svg.addRect(xStart+fullLength-19-shedLength,yStart+carportWidth-38,11,11); //nederste stolpe fremme
                svg.addRect(xStart+fullLength-10-(2*spærAfstand),yStart+carportWidth-38,11,11); //den hvor døren hægtes på
                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+32, 11,11); //midterste stolpe foroven før redskabsrum
                svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+carportWidth-38, 11,11); //midterste stolpe forneden før redskabsrum



                //spær nedre del
                svg.addRect(xStart+spærAfstand-3, yStart+carportWidth-38, 11,11); //første stolpe
                svg.addRect(xStart+fullLength-11, yStart+carportWidth-38, 11,11); //bagerste

                //streger om redskabsrum
                svg.addLine(xStart+carportLength-10, yStart+37,xStart+fullLength-11, yStart+37); //venstre side set forfra
                svg.addLine(xStart+carportLength-11,yStart+carportWidth-32,xStart+fullLength-11, yStart+carportWidth-32); //højre side set forfra
                svg.addLine(xStart+carportLength-10, yStart+37,xStart+carportLength-11,yStart+carportWidth-35); //forrest
                svg.addLine(xStart+fullLength-11, yStart+37, xStart+fullLength-11, yStart+carportWidth-32); //bagerst
                svg.addLine(xStart+fullLength-10-(2*spærAfstand),yStart+carportWidth-15, xStart+fullLength-5,yStart+carportWidth-35);//dør
                //append mål til koordinat??
            }

        }

        //Her er redskabsrummet IKKE valgt
        if(!erRedskabsRumValgt) {

            //udregn stolper, rem og spær MED redskabsrum og tegn svg
            // udregn stolper
            int antalSpær = (int) Math.ceil(carportLength/59.0);
            int spærAfstand = (int) Math.ceil(carportLength/antalSpær);

            //tegn carport ydre del
            svg.addRect(xStart,yStart,carportWidth, carportLength);

            //tegn kryds
            svg.addLine(xStart+5+spærAfstand, yStart+35,xStart+carportLength-5,yStart+carportWidth-30);
            svg.addLine(xStart+5+spærAfstand, yStart+carportWidth-30, xStart+carportLength-5, yStart+35);

            //tegn rem
            //øverste/venstre rem
            svg.addRect(xStart, yStart+35, 5, carportLength-5);

            //nederste/højre rem
            svg.addRect(xStart, yStart+carportWidth-35, 5, carportLength-5);

            //tegn spær
            int x = 0;
            int spærStartX = xStart;
            int spærStartY = yStart;
            int start = xStart;
            while(spærStartX < carportLength + start) {
                svg.addRect(spærStartX + spærAfstand * x, spærStartY,carportWidth, 5);
                spærStartX+=spærAfstand;
            }

            svg.addRect(xStart+carportLength-5, yStart, carportWidth,5);

            //tegn stolper
            //stolper øverste del
            svg.addRect(xStart+spærAfstand-3, yStart+32, 11,11); //første stolpe
            svg.addRect(xStart+carportLength-11, yStart+32, 11,11); //bagerste stolpe
            svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+32, 11,11); //midterste stolpe


            //spær nedre del
            svg.addRect(xStart+spærAfstand-3, yStart+carportWidth-38, 11,11); //første stolpe
            svg.addRect(xStart+carportLength-11, yStart+carportWidth-38, 11,11); //bagerste
            svg.addRect((xStart+(carportLength+spærAfstand-11)/2), yStart+carportWidth-38, 11,11); //midterste


            //append mål til koordinat??

        }

        session.setAttribute("svgdrawing", svg.toString());
        session.setAttribute("carportbredde", carportWidth);
        session.setAttribute("carportlængde", carportLength);
        session.setAttribute("carporthøjde", carportHojde);
        session.setAttribute("redskabsrumValgt", erRedskabsRumValgt);
        session.setAttribute("redskabsrumPlacering", placering);


        return "SeSkitse";
    }



}
