package downloadmanager;

import java.io.IOException;
import java.net.URL;

/**
 * A download connection is for connecting to and getting bytes from a server.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class DownloadConnection {

	/** The URL of the server this connection connects to. */
	protected URL mURL;

	/**
	 * Construct a download connection.
	 * @param URL The URL to connect to.
	 */
	protected DownloadConnection(URL url) {
		mURL = url;
	}

	/**
	 * Get the URL of this connection.
	 * @return The URL of this connection.
	 */
	public URL getURL() {
		return mURL;
	}

	/**
	 * Retrieve a set amount of bytes from the server.
	 * @param position The position to start from.
	 * @param bufferSize Max buffer size.
	 * @param totalSize The total size of the download.
	 * @return A byte array with the requested bytes.
	 * @throws java.io.IOException If there was an error retrieving the bytes.
	 */
	public abstract byte[] getBytes(long position, int bufferSize, long totalSize) throws java.io.IOException;

	/**
	 * Connect to the server.
	 * @throws UnableToConnectException if the connection was unsuccessful.
	 */
	public abstract int connect() throws UnableToConnectException;

	public abstract void close();
}
