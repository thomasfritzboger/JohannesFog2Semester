package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

abstract class Command
{

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("lavbruger", new LavBruger());
        commands.put("faq", new Faq());
        commands.put("kundeLogin",new KundeLogin());
        commands.put("carportDesigner", new CarportDesigner());
        commands.put("forespoergsler", new Forespoergsler());
        commands.put("kunder", new Kunder());
        commands.put("ordre", new Ordre());
        commands.put("lager", new Lager());
        commands.put("skitse", new Skitse());
        commands.put("profil", new Profil());
        commands.put("stykliste", new Stykliste());
        commands.put("updateCarportPrice", new UpdateCarportPrice());
        commands.put("confirmCarportRequest", new ConfirmCarportRequest());
    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command");
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws DatabaseException;

}