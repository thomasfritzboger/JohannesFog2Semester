package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderCommand extends Command {
    List<RequestDTO> requestApproved;
    private ConnectionPool connectionPool;

    public OrderCommand() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("admin")) {
            return "error";
        }

        requestApproved = AdminFacade.getApprovedRequest(connectionPool);

        session = request.getSession();

        session.setAttribute("requestApproved", requestApproved);

        return "order";
    }
}
