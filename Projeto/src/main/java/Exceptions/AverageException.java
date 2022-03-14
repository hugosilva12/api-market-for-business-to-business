package Exceptions;

/**
 * This exception will be returned when it is not possible to obtain statistical metrics
 */
public class AverageException extends RuntimeException {

    public AverageException(String msg) {
        super(msg);
    }
}
