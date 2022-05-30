package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateUserCommand extends Command
{
    private ConnectionPool connectionPool;

    public CreateUserCommand()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException
    {
        User user = null;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("user", null);

            String email = request.getParameter("newEmail");
            String password = request.getParameter("newPassword");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            int postalNumber = Integer.parseInt(request.getParameter("postalCode"));

            user = UserFacade.createUser(email, password, "kunde", phoneNumber, address, postalNumber, connectionPool);

            user = UserFacade.login(email, password, connectionPool);

            session = request.getSession();

            session.setAttribute("user", user); // adding user object to session scope
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            return "error";
        }

        return "customerLogin";
    }
}