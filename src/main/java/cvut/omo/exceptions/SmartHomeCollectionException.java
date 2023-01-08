package cvut.omo.exceptions;

public class SmartHomeCollectionException extends RuntimeException {
    public SmartHomeCollectionException(String str) {
        super(str);
    }

    public SmartHomeCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
