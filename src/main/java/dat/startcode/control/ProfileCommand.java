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

public class ProfileCommand extends Command {

    Shed shed;
    private ConnectionPool connectionPool;

    public ProfileCommand() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        int coverageId = 40;
        String roofType = (String) session.getAttribute("roofType");
        int userId = ((User) session.getAttribute("user")).getUserId();

        String updatedEmail = request.getParameter("enterNewEmail");
        String newPassword = request.getParameter("newPassword");
        String newPhoneNumber = request.getParameter("newPhoneNumber");

        if(updatedEmail != null) {
            CustomerFacade.updateEmail(userId,updatedEmail,connectionPool);
        }

        if(newPassword != null) {
            CustomerFacade.updatePassword(userId,newPassword,connectionPool);
        }

        if(newPhoneNumber != null) {
            CustomerFacade.updatePhoneNumber(userId,newPhoneNumber,connectionPool);
        }

        //laver et tjek på carportbredde for at se om vi kommer fra en forespørgsel eller forsøger
        //at tilgå profilsiden fra menuen
        if(session.getAttribute("carportWidth") != null) {
            int width = (int) session.getAttribute("carportWidth");
            int length = (int) session.getAttribute("carportLength");
            int height = (int) session.getAttribute("carportHeight");
            boolean hasShed;
            int shedId;
            if (session.getAttribute("shedChosen") != null
                    && session.getAttribute("shedChosen").equals("y")) {
                hasShed = true;
                //stempel ned i shed tabel med width, length og placement
                int shedWidth = (int) session.getAttribute("shedWidth");
                int shedLength = (int) session.getAttribute("shedLength");
                String shedPlacement = (String) session.getAttribute("shedPlacement");

                shed = CustomerFacade.createNewShed(shedWidth, shedLength, shedPlacement, connectionPool);
                shedId = shed.getShedId();
            } else {
                hasShed = false;
                shedId = -1;
            }

            boolean isConfirmed = false;

            //createCarportRequest
            Request carportRequest = CustomerFacade.createCarportRequest(coverageId, userId, width, length, height, roofType, hasShed, shedId, isConfirmed, (Double) session.getAttribute("carportPrice"), connectionPool);
        }

        //bruges til at loade på profilside
        List<Request> userRequests = CustomerFacade.getCarportRequestById(user.getUserId(), connectionPool);
        session = request.getSession();

        session.setAttribute("carportWidth", null);
        session.setAttribute("carportLength", null);
        session.setAttribute("carportHeight", null);
        session.setAttribute("shedChosen", null);
        session.setAttribute("shedPlacement", null);

        session.setAttribute("carportRequestByUser", userRequests);

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "profile";
    }
}