package downloadmanager.gui;

import downloadmanager.DownloadEvent;
import downloadmanager.DownloadObject;
import downloadmanager.DownloadObserver;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue implements DownloadObserver {

    /** The list of download objects. */
    private ArrayList<DownloadObject> mDownloads;
    /** The list of progress bars. */
    private ArrayList<JProgressBar> mProgressBars;

    /**
     * Construct a download queue.
     */
    public DownloadQueue() {
	mDownloads = new ArrayList<DownloadObject>();
	mProgressBars = new ArrayList<JProgressBar>();
    }

    /**
     * @see DownloadObserver#downloadEventPerformed(DownloadEvent downloadEvent)
     */
    public void downloadEventPerformed(DownloadEvent downloadEvent) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Add a download to the list.
     * @param downloadObject The download object to add.
     */
    public void addDownload(DownloadObject downloadObject) {
	mDownloads.add(downloadObject);
	mProgressBars.add(new JProgressBar());
    }
}
