package cvut.omo.exceptions;

/**
 *
 */
public class FloorException extends RuntimeException{

    public FloorException(String str) {
        super(str);
    }
    public FloorException(String message, Throwable cause) {
        super(message, cause);
    }
}
