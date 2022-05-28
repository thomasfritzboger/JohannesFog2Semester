package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCarportPriceCommand extends Command{
    private ConnectionPool connectionPool;

    public UpdateCarportPriceCommand() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        try {
            HttpSession session;

            int carportId = Integer.parseInt(request.getParameter("carportId"));
            int newCoverage = Integer.parseInt(request.getParameter("newCoverage"));

            Carport newCarportCoverage = AdminFacade.updateCarportPrice(newCoverage,carportId,connectionPool);

            List<RequestDTO> carportRequest = AdminFacade.getRequest(connectionPool);
            session = request.getSession();

            session.setAttribute("newCarportCoverage", newCarportCoverage);
            session.setAttribute("carportRequest", carportRequest);
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            return "error";
        }

        return "requestList";
    }
}
