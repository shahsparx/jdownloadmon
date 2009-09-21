package downloadmanager;

/**
 * The FTP download connection to handle FTP connections.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class FTPDownloadConnection extends DownloadConnection {

    /**
     * Construct a FTP download connection.
     * @param URL The FTP server's url.
     */
    public FTPDownloadConnection(String URL) {
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
