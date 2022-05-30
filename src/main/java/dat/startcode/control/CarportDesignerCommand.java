package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarportDesignerCommand extends Command{

    private ConnectionPool connectionPool;

    public CarportDesignerCommand() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    int[] carportWidth = {300,320,340,360,380,400,420,440,460,480,500,520,540,560,580,600};
    int[] carportLength = {420,440,460,480,500,520,540,560,580,600};
    int[] carportHeight = {210,220,230,240,250,260,270,280,290,300};

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        List<ProductDTO> productDTOList;
        productDTOList = ProductFacade.getProductDTOs(connectionPool);


        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        session = request.getSession();
        session.setAttribute("productDTOList", productDTOList);
        session.setAttribute("carportWidthList", carportWidth);
        session.setAttribute("carportLengthList", carportLength);
        session.setAttribute("carportHeightList", carportHeight);

        if(session.getAttribute("carportWidth") != null) {
            session.setAttribute("svgDrawing", null);
            session.setAttribute("carportWidth", null);
            session.setAttribute("carportLength", null);
            session.setAttribute("carportHeight", null);
            session.setAttribute("shedChosen", null);
            session.setAttribute("shedPlacement", null);
            session.setAttribute("shedLength", null);
        }

        return "carportDesigner";
    }
}