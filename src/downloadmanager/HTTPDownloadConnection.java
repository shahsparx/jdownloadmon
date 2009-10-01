package downloadmanager;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * The HTTP download connection handles connections to HTTP servers.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class HTTPDownloadConnection extends DownloadConnection {

	/** The input stream used to read bytes from the server. */
	BufferedInputStream mStream;
	/** The actual HTTP connection. */
	HttpMethod mRequest;

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
	public long connect(long downloaded) throws IOException {
		HttpClient client = new HttpClient();
		mRequest = new GetMethod(mURL.toString());
		if (downloaded > 0L) {
			// server must support partial content for resume
			mRequest.addRequestHeader("Range", "bytes=" + downloaded + "-");
			if (client.executeMethod(mRequest) != HttpStatus.SC_PARTIAL_CONTENT) {
				throw new IOException("Server doesn't support partial content, or local file size >= remote file size");
			}
		} else if (client.executeMethod(mRequest) != HttpStatus.SC_OK) {
			// response not ok
			throw new IOException("Cannot retrieve file from server.");
		}
		Header contentLengthHeader = mRequest.getResponseHeader("content-length");
		if (contentLengthHeader == null) {
			throw new IOException("Cannot retrieve file from server.");
		}

		mStream = new BufferedInputStream(mRequest.getResponseBodyAsStream());
		return Long.parseLong(contentLengthHeader.getValue());
	}

	@Override
	public void close() {
		try {
			if (mStream != null) {
				mStream.close();
			}
			if (mRequest != null) {
				mRequest.releaseConnection();
			}
		} catch (Exception e) {
			//ignore
		}
	}

	@Override
	public DownloadConnection getDeepCopy() throws MalformedURLException {
		URL url = new URL(mURL.toString());
		return new HTTPDownloadConnection(url);
	}
}
