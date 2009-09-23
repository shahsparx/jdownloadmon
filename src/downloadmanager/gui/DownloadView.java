package downloadmanager.gui;

import downloadmanager.DownloadObject;
import javax.swing.JProgressBar;

/**
 * A download component is a viewable download object.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadView {

	/** The download object wrapped in this download view. */
	private DownloadObject mDownloadObject;
	/** The progress bar for this download component. */
	private JProgressBar mProgressBar;

	/**
	 * Construct a download component.
	 * @param downloadObject The download object to be viewed.
	 */
	public DownloadView (DownloadObject downloadObject) {
		mDownloadObject =  downloadObject;
		mProgressBar = new JProgressBar(0, 100);
	}

	/**
	 * @return This download view's progress bar.
	 */
	public JProgressBar getProgressBar() {
		return mProgressBar;
	}

	/**
	 * Set the value of the progress bar.
	 * @param percentage The percentage to set it to.
	 */
	public void setProgressBarValue(int percentage) {
		mProgressBar.setValue(percentage);
	}

	/**
	 * Get the file name of the download object.
	 * @return The file name and extension of the download object as a <tt>String</tt>.
	 */
	public String getFileName() {
		String[] strings = mDownloadObject.getDestination().split("/");
		return strings[strings.length-1];
	}
}
