package downloadmanager;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to test HTTPDownloadConnection class.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class HTTPDownloadConnectionTest {

	/**
	 * Test of getBytes method, of class HTTPDownloadConnection.
	 * Downloads a file and tests its Adler32 checksum against a (hopefully) identical existing file.
	 *
	 * @throws Exception if there was an error reading, writing or deleting files.
	 */
	@Test
	public void testGetBytes() throws Exception {
		HTTPDownloadConnection connection = new HTTPDownloadConnection(new URL("http://googleisagiantrobot.com/google-is-a-giant-robot.png"));
		String downloadFile = "test/downloadmanager/download.png";
		String goodFile = "test/downloadmanager/test.png";

		// Compute Adler-32 checksum
		CheckedInputStream cis = new CheckedInputStream(
				new FileInputStream(goodFile), new Adler32());
		byte[] tempBuf = new byte[128];
		while (cis.read(tempBuf) >= 0) {
		}
		long goodCheckSum = cis.getChecksum().getValue();
		cis.close();

		long downloaded = 0L;
		long totalSize = connection.connect(downloaded);
		int bufferSize = 1024;
		DownloadFile file = new DownloadFile(downloadFile, downloaded);
		while (downloaded < totalSize) {
			byte[] result = connection.getBytes(downloaded, totalSize, bufferSize);
			file.write(result);
			downloaded += result.length;
			if (result.length < bufferSize) {
				assertEquals(downloaded, totalSize);
			}
		}
		file.close();
		connection.close();

		// Compute Adler-32 checksum
		CheckedInputStream cis2 = new CheckedInputStream(
				new FileInputStream(downloadFile), new Adler32());
		tempBuf = new byte[128];
		while (cis2.read(tempBuf) >= 0) {
		}
		long downloadCheckSum = cis2.getChecksum().getValue();
		cis2.close();
		File deleteFile = new File(downloadFile);
		deleteFile.delete();

		assertEquals(goodCheckSum,  downloadCheckSum);
	}
}
