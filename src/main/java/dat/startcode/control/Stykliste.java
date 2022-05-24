package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Stykliste extends Command {


    private ConnectionPool connectionPool;
    public Stykliste () {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        int carportId = Integer.parseInt(request.getParameter("getcarportid"));

        List<OrderLineDTO> materialLineList = ProductFacade.getMaterialLinesByCarportId(connectionPool, carportId);

        session.setAttribute("materiallinelist",materialLineList);

        return "stykliste";
    }
}
