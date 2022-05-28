package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ItemListCommand extends Command {

    private ConnectionPool connectionPool;
    public ItemListCommand() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        int carportId = Integer.parseInt(request.getParameter("getCarportId0"));

        List<OrderLineDTO> materialLineList = ProductFacade.getMaterialLinesByCarportId(connectionPool, carportId);

        session.setAttribute("materialLineList",materialLineList);

        return "itemList";
    }
}
