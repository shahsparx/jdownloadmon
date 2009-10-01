package downloadmanager;

import java.io.IOException;
import java.net.URL;

/**
 * The FTP download connection to handle FTP connections.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class FTPDownloadConnection extends DownloadConnection {

	/**
	 * Construct a FTP download connection.
	 * @param url The FTP server's url.
	 */
	public FTPDownloadConnection(URL url) {
		super(url);
	}

	@Override
	public byte[] getBytes(long downloaded, long totalSize, int bufferSize) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void close() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getSingleByte() throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DownloadConnection getDeepCopy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public long connect(long downloadedSize) throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
