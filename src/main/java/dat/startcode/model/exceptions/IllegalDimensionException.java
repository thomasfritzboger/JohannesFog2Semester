package dat.startcode.model.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IllegalDimensionException extends Exception {
    public IllegalDimensionException(String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message);
    }
    public IllegalDimensionException(Exception ex, String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message, ex.getMessage());
    }
}

