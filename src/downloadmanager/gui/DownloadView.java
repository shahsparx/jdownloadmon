package downloadmanager.gui;

import downloadmanager.DownloadObject;
import javax.swing.JProgressBar;

/**
 * A download component is a viewable download object.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadView {

	/** The download object wrapped in this download component. */
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
}
