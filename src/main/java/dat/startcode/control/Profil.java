package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Profil extends Command {

    List<Carport> carportListe;
    List<OrderLineDTO> orderLineDTOList;

    private ConnectionPool connectionPool;

    public Profil() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        carportListe = UserFacade.getCarportByUser(user.getUserId(),connectionPool);
        orderLineDTOList = (List<OrderLineDTO>) session.getAttribute("orderLineDTOList");

        int carportSamletPris = 0;

        for (OrderLineDTO orderLineDTO : orderLineDTOList) {

            carportSamletPris += orderLineDTO.totalPrice;

        }

        session = request.getSession();

        session.setAttribute("carportListe", carportListe);
        session.setAttribute("carportSamletPris",carportSamletPris);


        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "profil";
    }
}
