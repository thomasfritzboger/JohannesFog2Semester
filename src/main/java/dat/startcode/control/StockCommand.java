package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.StockDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StockCommand extends Command {

    List<StockDTO> stockDTOList;

    private ConnectionPool connectionPool;

    public StockCommand() {this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(!user.getRole().equals("admin")) {
            return "error";
        }

        if (request.getParameter("productId") != null && request.getParameter("newPrice") != null) {
            int productID = Integer.parseInt(request.getParameter("productId"));
            double newPrice = Double.parseDouble(request.getParameter("newPrice"));
            ProductFacade.updateUnitPrice(connectionPool, productID, newPrice);
        }

        if (request.getParameter("description") != null && request.getParameter("newDescription") != null) {
            int productID = Integer.parseInt(request.getParameter("description"));
            String newDescription = request.getParameter("newDescription");
            ProductFacade.updateProductDescription(connectionPool,productID,newDescription);
        }

        stockDTOList = ProductFacade.getStock(connectionPool);

        session.setAttribute("stockList", stockDTOList);

        return "stock";
    }
}
