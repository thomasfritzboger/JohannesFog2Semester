package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.AdminMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AdminFacade;
import dat.startcode.model.services.CarportCalculator;
import dat.startcode.model.services.CustomerFacade;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

        Carport carport = CustomerFacade.getCarportById(carportId, connectionPool);
        Shed shed = carport.getShed();

        List<OrderLineDTO> materialLine = new ArrayList<>();
        CarportCalculator calculator = new CarportCalculator(ProductFacade.getProduktDTOs(connectionPool));

        try {
            if (shed==null) {
                materialLine = calculator.beregnCarport(carport.getCarportLength(),carport.getCarportWidth(),carport.getCarportHeight(), "n", "p",null,0);
            } else {
                int spærAntal = (int) Math.ceil(carport.getCarportLength()/59.0);
                int spærAfstand = (int) Math.ceil(carport.getCarportLength()/spærAntal);
                int shedSize = (int) Math.ceil(shed.getLength()/spærAfstand);
                materialLine = calculator.beregnCarport(carport.getCarportLength(),carport.getCarportWidth(),carport.getCarportHeight(), "y", "p",shed.getPlacement(),shedSize);
            }
        } catch (IllegalDimensionException e) {
            e.printStackTrace();
        }

        for (OrderLineDTO i : materialLine) {
            ProductFacade.saveMaterialLines(connectionPool,carportId,i.getProductId(),i.getLength(),i.getAmount(),i.getTotalPrice());
        }

        session.setAttribute("carportRequest",carportRequest);
        return "forespoergsler";
    }
}
