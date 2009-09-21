package downloadmanager;

/**
 * The HTTP download connection handles connections to HTTP servers.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class HTTPDownloadConnection extends DownloadConnection {

    /**
     * Construct a HTTP Download Connection.
     * @param URL The URL to the server.
     */
    public HTTPDownloadConnection(String URL) {
	super(URL);
    }

    @Override
    public int[] getBytes(int position, int size) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connect() throws UnableToConnectException {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
