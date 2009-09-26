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
	public byte[] getBytes(long position, int bufferSize, long totalSize) throws java.io.IOException {
		byte buffer[];
		if (totalSize - position >= bufferSize) {
			buffer = new byte[bufferSize];
		} else {
			buffer = new byte[(int) (totalSize - position)];
		}

		int read = mStream.read(buffer);

		if (read == -1) {
			throw new java.io.IOException("Stream read error");
		}
		return buffer;
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
	public int connect() throws UnableToConnectException {
		try {
			System.out.println("connecting");
			/*System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "proxy.student.jenseneducation.se");
			System.setProperty("http.proxyPort", "8080");
			System.setProperty("http.proxyType", "4");
			Authenticator.setDefault(new MyAuthenticator());
			 */

			mConnection = (HttpURLConnection) mURL.openConnection();
			mConnection.connect();
			mStream = mConnection.getInputStream();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
