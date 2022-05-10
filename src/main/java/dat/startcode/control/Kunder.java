package dat.startcode.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Kunder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        return "kunder";
    }

}
