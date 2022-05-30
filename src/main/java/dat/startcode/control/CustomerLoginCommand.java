package dat.startcode.control;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLoginCommand extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("kunde")) {
            return "error";
        }

        return "customerLogin";
    }
}
