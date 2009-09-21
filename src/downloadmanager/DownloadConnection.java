package downloadmanager;

/**
 * A download connection is for connecting to and getting bytes from a server.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class DownloadConnection {

    /** The URL of the server this connection connects to. */
    protected String mURL;

    /**
     * Construct a download connection.
     * @param URL The URL to connect to.
     */
    protected DownloadConnection(String URL) {
	mURL = URL;
    }

    /**
     * Get the URL of this connection.
     * @return The URL of this connection.
     */
    public String getURL() {
	return mURL;
    }

    /**
     * Get some bytes from the server.
     * @param position The position to start from.
     * @param size How many bytes to get.
     * @return an array of the bytes.
     */
    public abstract int[] getBytes(int position, int size);

    /**
     * Connect to the server.
     * @throws UnableToConnectException if the connection was unsuccessful.
     */
    public abstract void connect() throws UnableToConnectException;
}
