package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class Kunder extends Command {

    List<User> kundeListe;
    User searchedCustomer = null;

    private ConnectionPool connectionPool;

    public Kunder()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        kundeListe = UserFacade.getCustomerList(connectionPool);

        HttpSession session = request.getSession();

        String email = request.getParameter("soegt-email");
        boolean emailFound = false;

        if(email != null) {
            for (User user : kundeListe) {
                if(user.getEmail().equals(email)) {
                    searchedCustomer = user;
                    emailFound = true;
                    break;
                }
                searchedCustomer = null;
            }
        }

        session = request.getSession();

        session.setAttribute("customerlist", kundeListe);
        session.setAttribute("searchedcustomer", searchedCustomer);
        session.setAttribute("soegt-email", email);

        return "kunder";
    }

}
