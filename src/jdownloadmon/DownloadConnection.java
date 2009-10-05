package jdownloadmon;

import java.io.IOException;
import java.net.MalformedURLException;
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
	 * @param url The URL to connect to.
	 */
	protected DownloadConnection(URL url) {
		mURL = url;
	}

	/**
	 * Get a file's filename from this connection's url.
	 * @return The file's filename as a String.
	 */
	public String getFileName() {
		String fileName = mURL.getFile();
		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}

	/**
	 * Get the URL of this connection.
	 * @return The URL of this connection.
	 */
	public URL getURL() {
		return mURL;
	}

	/**
	 * Retrieve a single byte from the server.
	 * @return The requested byte as an <tt>int</tt>.
	 * @throws IOException if there was an error retrieving the byte.
	 */
	public abstract int getSingleByte() throws IOException;

	/**
	 * Retrieve a set amount of bytes from the server.
	 * @param downloaded The position to start from.
	 * @param bufferSize Max buffer size.
	 * @param totalSize The total size of the download.
	 * @return A byte array with the requested bytes.
	 * @throws IOException if there was an error retrieving the bytes.
	 * @throws InterruptedException if thread was interrupted while trying to sleep.
	 */
	public abstract byte[] getBytes(long downloaded, long totalSize, int bufferSize) throws IOException, InterruptedException;

	/**
	 * Connect to the server.
	 * @param downloadedSize How much is already downloaded.
	 * @return The total size of the download.
	 * @throws IOException if the connection was unsuccessful.
	 */
	public abstract long connect(long downloadedSize) throws IOException;
	
	/**
	 * Get the full size of the download.
	 * @return The full size of the download in bytes.
	 * @throws IOException If there was a connection problem.
	 */
	public abstract long getSize() throws IOException;

	/**
	 * Close the connection and any associated streams.
	 */
	public abstract void close();

	/**
	 * @return a copy of this download connection.
	 * @throws MalformedURLException if the URL contained in this URL was errenous.
	 */
	public abstract DownloadConnection getDeepCopy() throws MalformedURLException;
}
