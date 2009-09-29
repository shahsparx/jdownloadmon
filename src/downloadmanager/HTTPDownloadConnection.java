package downloadmanager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The HTTP download connection handles connections to HTTP servers.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class HTTPDownloadConnection extends DownloadConnection {

	/** The input stream used to read bytes from the server. */
	InputStream mStream;
	/** The actual HTTP connection. */
	HttpURLConnection mConnection;

	/**
	 * Construct a HTTP Download Connection.
	 * @param URL The URL to the server.
	 */
	HTTPDownloadConnection(URL url) {
		super(url);
	}

	@Override
	public byte[] getBytes(long downloaded, long totalSize, int bufferSize) throws java.io.IOException, InterruptedException {
		byte[] buffer;
		int readSize;
		
		if (totalSize - downloaded > bufferSize) {
			readSize = bufferSize;
		} else {
			readSize = (int) (totalSize - downloaded);
		}

		// Check if there is sufficient data downloaded
		while (mStream.available() < readSize) {
			Thread.sleep(10); // sleep if not yet ready
		}
		
		// read
		buffer = new byte[readSize];
		int read = mStream.read(buffer);

		// Finally, check to make sure something actually got read.
		if (read == -1) {
			throw new IOException("Stream read error");
		}

		return buffer;
	}

	@Override
	public int getSingleByte() throws IOException {
		int read = mStream.read();
		if (read == -1) {
			throw new java.io.IOException("Stream read error");
		}
		return read;
	}

	@Override
	public int connect(long downloaded) throws IOException {
			mConnection = (HttpURLConnection) mURL.openConnection();
			mConnection.connect();
			mStream = mConnection.getInputStream();
			mStream.skip(downloaded);
			return mConnection.getContentLength();
	}

	@Override
	public void close() {
		if (mStream != null) {
			try {
				mStream.close();
				mConnection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
