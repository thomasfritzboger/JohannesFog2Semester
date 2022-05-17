package dat.startcode.control;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.services.CarportCalculator;
import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        //tagmateriale
        String tagType = request.getParameter("tagtype");

        //placering
        String placering = request.getParameter("redskabsrumPlacering");

        //skurSize
        int skurSize = Integer.parseInt(request.getParameter("skurSize"));

        CarportCalculator calculator = new CarportCalculator();




        // udregn stolper

        //udregn rem

        //udregn spær



        //er redskabsrum tillagt så hent

        //redskabsrum placering

        //redskabsrum bredde

        //redskabsrum længde


        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 50);

        for (int x = 0; x < 14; x++)
        {
            svg.addRect(100 + 50 * x, 0, 600.0, 4.5);
            svg.addLine(5,5,100,100);
        }

        session.setAttribute("svgdrawing", svg.toString());



        return "skitse";
    }
}
