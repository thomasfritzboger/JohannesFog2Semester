package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Profil extends Command {

    Request request;
    Shed shed;

    private ConnectionPool connectionPool;

    public Profil() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        int coverageId = 40;
        int userId = ((User) session.getAttribute("user")).getUserId();

        String updatedEmail = request.getParameter("indtastNyEmail");
        String newPass = request.getParameter("passwordny");
        String newPhoneNumber = request.getParameter("nyttelefonnr");

        if(updatedEmail != null) {
            CustomerFacade.updateEmail(userId,updatedEmail,connectionPool);
        }

        if(newPass != null) {
            CustomerFacade.updatePass(userId,newPass,connectionPool);
        }

        if(newPhoneNumber != null) {
            CustomerFacade.updatePhoneNumber(userId,newPhoneNumber,connectionPool);
        }

        //Owais arbejder her
        /*System.out.println(session.getAttribute("forespørgselbekræfter") != null);
        System.out.println(session.getAttribute("forespørgselbekræfter"));
        System.out.println(session.getAttribute("forespørgselbekræfter").equals("send"));*/


        //laver et tjek på carportbredde for at se om vi kommer fra en forespørgsel eller forsøger
        //at tilgå profilsiden fra menuen
        if(session.getAttribute("carportbredde") != null) {
            System.out.println("her når vi 1");
            int width = (int) session.getAttribute("carportbredde");
            int length = (int) session.getAttribute("carportlængde");
            int height = (int) session.getAttribute("carporthøjde");
            boolean hasShed;
            int shedId;
            if (session.getAttribute("redskabsrumValgt") != null
                    && session.getAttribute("redskabsrumValgt").equals("y")) {
                hasShed = true;
                //stempel ned i shed tabel med width, length og placement
                int shedWidth = (int) session.getAttribute("redskabsrumbredde");
                int shedLength = (int) session.getAttribute("redskabsrumLængde");
                String shedPlacement = (String) session.getAttribute("redskabsrumPlacering");

                System.out.println("her når vi ikke");
                shed = CustomerFacade.createNewShed(shedWidth, shedLength, shedPlacement, connectionPool);
                shedId = shed.getShedId();
            } else {
                hasShed = false;
                shedId = -1;
            }

            boolean isConfirmed = false;
            System.out.println("Hello");
            //createCarportRequest
            System.out.println("width: " + width);
            System.out.println("length: " + length);
            System.out.println("height: " + height);
            System.out.println("hasShed: " + hasShed);
            System.out.println("shedId: " + shedId);
            System.out.println("Hello 0.5");
            Request carportRequest = CustomerFacade.createCarportRequest(coverageId, userId, width, length, height, hasShed, shedId, isConfirmed, (Double) session.getAttribute("carportPrice"), connectionPool);
            System.out.println(carportRequest);
            System.out.println("Hello 0.75");
        }

        //bruges til at loade på profilside
        List<Request> userRequests = CustomerFacade.getCarportRequestById(user.getUserId(), connectionPool);
        System.out.println("Hello 2");
        session = request.getSession();

        session.setAttribute("carportbredde", null);
        session.setAttribute("carportlængde", null);
        session.setAttribute("carporthøjde", null);
        session.setAttribute("redskabsrumValgt", null);
        session.setAttribute("redskabsrumPlacering", null);

        System.out.println("Hello 3");
        session.setAttribute("carportRequestByUser", userRequests);

        if(!user.getRole().equals("kunde")) {
            return "error";
        }
        System.out.println("Hello 4");
        return "profil";
    }
}
