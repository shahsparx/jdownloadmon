package downloadmanager;

/**
 * When unable to connect, this exception is thrown.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class UnableToConnectException extends Exception {

    /**
     * Construct the exception.
     * @param message The error message of this exception.
     */
    public UnableToConnectException(String message) {
	super(message);
    }
}
