package cvut.omo.exceptions;

public class OMOException  extends RuntimeException{
    public OMOException(String str) {
        super(str);
    }
    public OMOException(String message, Throwable cause) {
        super(message, cause);
    }
}
