package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CarportDesigner extends Command{

    private ConnectionPool connectionPool;

    public CarportDesigner() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    int[] carportWidth = {300,320,340,360,380,400,420,440,460,480,500,520,540,560,580,600};
    int[] carportLength = {420,440,460,480,500,520,540,560,580,600};
    int[] carportHeight = {210,220,230,240,250,260,270,280,290,300};
    //String[] redskabsRumPlacering = {"venstre", "højre", "midt"};
    int[] redskabsrumBredder = {210,220,230,240,250,260,270,280,290,300}; // skal fikses
    int[] redskabsrumLaengder = {210,220,230,240,250,260,270,280,290,300}; // skal fikses

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        System.out.println("inden getProduktDTOs.");

        List<ProduktDTO> productDTOListe = new ArrayList<>();
        productDTOListe = ProductFacade.getProduktDTOs(connectionPool);

        System.out.println(productDTOListe.get(0));

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        session = request.getSession();
        session.setAttribute("productDTOListe", productDTOListe);
        session.setAttribute("carportWidthList", carportWidth);
        session.setAttribute("carportLengthList", carportLength);
        session.setAttribute("carportHeightList", carportHeight);
        //session.setAttribute("carportRoomPlacements", redskabsRumPlacering);
        session.setAttribute("carportRoomWidthList", redskabsrumBredder);
        session.setAttribute("carportRoomLengthList", redskabsrumLaengder);

        return "carportDesigner";
    }
}
