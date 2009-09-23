package downloadmanager;

/**
 * Exception indicating an URL is not valid.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class MalformedURLException extends Exception {

	/**
	 * Construct a malformed URL exception.
	 * @param message The error message.
	 */
	public MalformedURLException(String message) {
		super(message);
	}
}
