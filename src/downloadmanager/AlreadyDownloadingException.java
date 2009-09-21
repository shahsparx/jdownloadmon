package downloadmanager;

/**
 * An exception class that signifies a download was already in progress when download parameters were being modified.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
class AlreadyDownloadingException extends Exception {

    /**
     * Construct a new already downloading exception.
     * @param message The message of the exception.
     */
    public AlreadyDownloadingException(String message) {
	super(message);
    }
}
