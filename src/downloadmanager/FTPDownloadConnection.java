package downloadmanager;

import java.net.URL;

/**
 * The FTP download connection to handle FTP connections.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class FTPDownloadConnection extends DownloadConnection {

	/**
	 * Construct a FTP download connection.
	 * @param URL The FTP server's url.
	 */
	public FTPDownloadConnection(URL url) {
		super(url);
	}

	@Override
	public int connect() throws UnableToConnectException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public byte[] getBytes(long position, int bufferSize, long totalSize) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void close() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
