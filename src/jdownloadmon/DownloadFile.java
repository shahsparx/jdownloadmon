package jdownloadmon;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The file handler writes to and reads from files on the hard drive.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadFile {

	/** The random access file this file handler uses. */
	private RandomAccessFile mFile;
	/** The random acess file's destination.*/
	private String mDestination;

	/**
	 * Construct a download file.
	 * @param destination The url the file is located at.
	 * @param position The position at which to start writing to local file.
	 * @throws IOException if position is less than 0 or an I/O error occurs.
	 */
	public DownloadFile(String destination, long position) throws IOException {
		mDestination = destination;

		File file = new File(mDestination);

		if (file.exists()) {
			if (DownloadManager.INSTANCE.getSettings().getDefaultFileExistsBehavior().equals(
					DownloadManager.DefaultFileExistsBehavior.REPLACE)) {
				file.delete();
			}
			if (DownloadManager.INSTANCE.getSettings().getDefaultFileExistsBehavior().equals(
					DownloadManager.DefaultFileExistsBehavior.RENAME)) {
				// For a new file, reset the position to 0
				position = 0;
				int i = 1;
				int pos = mDestination.lastIndexOf(".");
				if (pos == -1) {
					pos = mDestination.length();
				}

				String newPath;

				do {
					newPath = mDestination.substring(0, pos) + "(" + i + ")" + mDestination.substring(pos);
					file = new File(newPath);
					i++;
				} while (file.exists());

				mDestination = newPath;
			}
		}

		File dir = file.getParentFile();
		if (dir != null && !dir.isDirectory()) {
			dir.mkdir();
		}

		mFile = new RandomAccessFile(mDestination, "rw");
		mFile.seek(position);
	}

	/**
	 * Get a file's length if it exists.
	 * @param path The path to the file.
	 * @return The length of the file in bytes (0 if file is empty or doesn't exist).
	 */
	public static long getFileLength(String path) {
		File f = new File(path);
		if (f.exists()) {
			return f.length();
		}

		return 0;
	}

	/**
	 * @return The destination of this file.
	 */
	public String getDestination() {
		return mDestination;
	}

	/**
	 * Writes a single byte to a file.
	 * @param b The byte to write.
	 * @throws IOException if there was an error writing the byte.
	 */
	public void writeByte(int b) throws IOException {
		mFile.write(b);
	}

	/**
	 * Write a byte array to a file.
	 * @param buffer The array to write.
	 * @throws IOException if there was an error writing the array.
	 */
	public void write(byte[] buffer) throws IOException {
		mFile.write(buffer);
	}

	/**
	 * Close the random access file.
	 */
	public void close() {
		if (mFile != null) {
			try {
				mFile.close();
			} catch (IOException e) {
				//ignore
			}
		}
	}
}
