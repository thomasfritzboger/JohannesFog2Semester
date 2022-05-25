package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.LagerDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Lager extends Command {

    List<LagerDTO> lagerDTOList;

    private ConnectionPool connectionPool;

    public Lager() {this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(!user.getRole().equals("admin")) {
            return "error";
        }

        if (request.getParameter("produktId") != null && request.getParameter("nyPris") != null) {
            int produktID = Integer.parseInt(request.getParameter("produktId"));
            double nyPris = Double.parseDouble(request.getParameter("nyPris"));
            ProductFacade.updateLagerPrice(connectionPool, produktID, nyPris);
        }

        if (request.getParameter("description") != null && request.getParameter("nyBeskrivelse") != null) {
            int produktID = Integer.parseInt(request.getParameter("description"));
            String nyBeskrivelse = request.getParameter("nyBeskrivelse");
            ProductFacade.updateLagerDescription(connectionPool,produktID,nyBeskrivelse);
        }

        lagerDTOList = ProductFacade.getLager(connectionPool);

        session.setAttribute("LagerListe", lagerDTOList);

        return "lager";
    }
}
