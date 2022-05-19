package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.AdminMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmCarportRequest extends Command{

    private ConnectionPool connectionPool;
    private List<RequestDTO> carportRequest;

    public ConfirmCarportRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        int carportId = Integer.parseInt(request.getParameter("godkend"));

        AdminFacade.confirmCarportRequest(carportId,connectionPool);

        carportRequest = AdminFacade.getRequest(connectionPool);

        session.setAttribute("carportRequest",carportRequest);
        return "forespoergsler";
    }
}
