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
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("faq", new FaqCommand());
        commands.put("customerLogin",new CustomerLoginCommand());
        commands.put("carportDesigner", new CarportDesignerCommand());
        commands.put("requestList", new RequestListCommand());
        commands.put("customer", new CustomerCommand());
        commands.put("order", new OrderCommand());
        commands.put("stock", new StockCommand());
        commands.put("drawing", new DrawingCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("profile2", new ProfileFromMenuCommand());
        commands.put("itemList", new ItemListCommand());
        commands.put("updateCarportPrice", new UpdateCarportPriceCommand());
        commands.put("confirmCarportRequest", new ConfirmCarportRequestCommand());
        commands.put("viewDrawing", new ViewDrawingCommand());
        commands.put("deleteRequest", new DeleteRequestCommand());
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