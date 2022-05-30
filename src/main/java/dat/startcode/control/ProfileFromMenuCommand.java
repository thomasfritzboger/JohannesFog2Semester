package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileFromMenuCommand extends Command {

    private ConnectionPool connectionPool;

    public ProfileFromMenuCommand() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

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

        //bruges til at loade på profilside
        List<Request> userRequests = CustomerFacade.getCarportRequestById(user.getUserId(), connectionPool);

        session = request.getSession();
        session.setAttribute("carportRequestByUser", userRequests);

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "profile";
    }
}
