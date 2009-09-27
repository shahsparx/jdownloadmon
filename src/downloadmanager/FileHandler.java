package downloadmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

/**
 * The file handler writes to and reads from files on the hard drive.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class FileHandler {

	private RandomAccessFile mFile;

	public FileHandler(URL url, long position) throws FileNotFoundException, IOException {
		mFile = new RandomAccessFile(getFileName(url), "rw");
		mFile.seek(position);
	}

	private String getFileName(URL url) {
		String fileName = url.getFile();
		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}

	public void writeByte(int b) throws IOException {
		mFile.write(b);
	}

	public void write(byte[] buffer) throws IOException {
		mFile.write(buffer);
	}





	public void close() {
		if (mFile != null) {
			try {
				mFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
