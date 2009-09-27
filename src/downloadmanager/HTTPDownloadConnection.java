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

	InputStream mStream;
	HttpURLConnection mConnection;

	/**
	 * Construct a HTTP Download Connection.
	 * @param URL The URL to the server.
	 */
	HTTPDownloadConnection(URL url) {
		super(url);
	}

	@Override
	public byte[] getBytes(long downloaded, long totalSize, int bufferSize) throws java.io.IOException {
		byte[] buffer;
		
		if (totalSize - downloaded > bufferSize) {
			buffer = new byte[bufferSize];
		} else {
			buffer = new byte[(int) (totalSize - downloaded)];
		}

		int read = mStream.read(buffer);

		if (read == -1) {
			throw new java.io.IOException("Stream read error");
		}

		return buffer;
	}

	public int getSingleByte() throws IOException {
		int read = mStream.read();
		if (read == -1) {
			throw new java.io.IOException("Stream read error");
		}
		return read;
	}

	/*private class MyAuthenticator extends Authenticator {

		public MyAuthenticator() {
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			System.out.println("getPasswordAuthentication() called for https connection!!!");
			return new PasswordAuthentication("EdLa_ookaug08", "Zkelet4r6".toCharArray());
		}
	}*/

	@Override
	public int connect(long downloaded) throws UnableToConnectException {
		try {
			mConnection = (HttpURLConnection) mURL.openConnection();
			mConnection.connect();
			mStream = mConnection.getInputStream();
			mStream.skip(downloaded);
			return mConnection.getContentLength();
		} catch (IOException ex) {
			throw new UnableToConnectException(ex.getMessage());
		}
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
