package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomerCommand extends Command {

    List<User> customerList;
    User searchedCustomer = null;

    private ConnectionPool connectionPool;

    public CustomerCommand()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("admin")) {
            return "error";
        }

        String email = request.getParameter("searchedEmail");

        customerList = AdminFacade.getCustomerList(connectionPool);

        //søg efter eksisterende email i vores kundeliste
        if(email != null) {
            for (User u : customerList) {
                if(u.getEmail().equals(email)) {
                    searchedCustomer = u;
                    break;
                }
                searchedCustomer = null;
            }
        }

        session = request.getSession();

        session.setAttribute("customerList", customerList);
        session.setAttribute("searchedCustomer", searchedCustomer);
        session.setAttribute("searchedEmail", email);

        return "customer";
    }

}
