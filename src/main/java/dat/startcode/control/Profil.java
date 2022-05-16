package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
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

    private ConnectionPool connectionPool;

    public Profil() { this.connectionPool = ApplicationStart.getConnectionPool(); }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        carportListe = UserFacade.getCarportByUser(user.getUserId(),connectionPool);

        session = request.getSession();

        session.setAttribute("carportListe", carportListe);

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "profil";
    }
}
