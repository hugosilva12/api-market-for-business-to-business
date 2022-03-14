package ModuleTransaction.Orgcom.hashing;

/**
 * Exception thrown when a hashable object is not hashable.
 */
public class UnHashableException extends RuntimeException {
    /**
     * Constructs a new exception
     */
    public UnHashableException() {
        super();
    }

    /**
     * Constructs a new exception with a message
     *
     * @param message the message
     */
    public UnHashableException(String message) {
        super(message);
    }
}
