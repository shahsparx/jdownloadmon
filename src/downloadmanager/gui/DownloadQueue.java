package downloadmanager.gui;

import downloadmanager.DownloadEvent;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue extends JTable {

	/** The list of download views. */
	private ArrayList<DownloadView> mDownloads;

	/**
	 * Construct a download queue.
	 */
	public DownloadQueue() {
		mDownloads = new ArrayList<DownloadView>();
	}

	/**
	 * Add a download to the list.
	 * @param downloadComponent The download view to add.
	 */
	public void addDownload(DownloadView downloadView) {
		mDownloads.add(downloadView);
	}

	/**
	 * Update a download with the specified event.
	 * @param downloadEvent The download event that was performed.
	 */
	public void update(DownloadEvent downloadEvent) {
	}
}
