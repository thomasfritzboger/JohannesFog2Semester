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

public class ProfilFraMenu extends Command {

    Request request;
    Shed shed;

    private ConnectionPool connectionPool;

    public ProfilFraMenu() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

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

        //bruges til at loade på profilside
        List<Request> userRequests = CustomerFacade.getCarportRequestById(user.getUserId(), connectionPool);

        session = request.getSession();
        session.setAttribute("carportRequestByUser", userRequests);

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "profil";
    }
}
