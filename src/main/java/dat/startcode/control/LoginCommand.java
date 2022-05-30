package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.AdminFacade;
import dat.startcode.model.services.UserFacade;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCommand extends Command
{
    private ConnectionPool connectionPool;

    public LoginCommand()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException
    {
        User user = null;
        List<RequestDTO> carportRequest;

        try {
            HttpSession session = request.getSession();
            session.setAttribute("user", null);

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            user = UserFacade.login(email, password, connectionPool);

            session = request.getSession();
            session.setAttribute("user", user);
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            return "error";
        }

        if(user.getRole().equals("admin")) {
            HttpSession session = request.getSession();
            carportRequest = AdminFacade.getRequest(connectionPool);
            session.setAttribute("carportRequest",carportRequest);
            return "requestList";
        } else {
            return "customerLogin";
        }
    }
}