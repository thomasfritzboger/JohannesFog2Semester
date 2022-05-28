package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteRequestCommand extends Command{

    private ConnectionPool connectionPool;

    private List<RequestDTO> carportRequest;

    public DeleteRequestCommand() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        int carportId = Integer.parseInt(request.getParameter("decline"));

        AdminFacade.deleteRequest(connectionPool,carportId);

        carportRequest = AdminFacade.getRequest(connectionPool);

        session.setAttribute("carportRequest",carportRequest);

        return "requestList";
    }
}
