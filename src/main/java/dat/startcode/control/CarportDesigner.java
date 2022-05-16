package dat.startcode.control;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CarportDesigner extends Command{

    int[] carportWidth = {300,320,340,360,380,400,420,440,460,480,500,520,540,560,580,600};
    int[] carportLength = {420,440,460,480,500,520,540,560,580,600,620,640,660,680, 700,720,740,760,780,800,820,840,860,880,900,920,940,960,980,1000};
    int[] carportHeight = {210,220,230,240,250,260,270,280,290,300};
    //String[] redskabsRumPlacering = {"venstre", "højre", "midt"};
    int[] redskabsrumBredder = {210,220,230,240,250,260,270,280,290,300}; // skal fikses
    int[] redskabsrumLaengder = {210,220,230,240,250,260,270,280,290,300}; // skal fikses

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {


        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getRole().equals("kunde")) {
            return "error";
        }


        session = request.getSession();
        session.setAttribute("carportWidthList", carportWidth);
        session.setAttribute("carportLengthList", carportLength);
        session.setAttribute("carportHeightList", carportHeight);
        //session.setAttribute("carportRoomPlacements", redskabsRumPlacering);
        session.setAttribute("carportRoomWidthList", redskabsrumBredder);
        session.setAttribute("carportRoomLengthList", redskabsrumLaengder);

        return "carportDesigner";
    }
}
