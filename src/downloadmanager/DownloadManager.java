package downloadmanager;

import java.util.ArrayList;

/**
 * The download manager keeps track of download objects and moves them around its lists.
 * [singleton]
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadManager implements DownloadObserver {

	/** The singleton instance of this DownloadManager. */
	public static final DownloadManager INSTANCE = new DownloadManager();
	/** Variable representing how many downloads can be active at once. */
	private int mMaxDownloads = 3;
	/** List of active download objects. */
	private ArrayList<DownloadObject> mActiveList;
	/** List of inactive download objects. */
	private ArrayList<DownloadObject> mInactiveList;
	/** List of pending download objects. */
	private ArrayList<DownloadObject> mPendingList;
	/** List of completed download objects. */
	private ArrayList<DownloadObject> mCompletedList;
	/** List of download objects with errors. */
	private ArrayList<DownloadObject> mErrorList;

	/**
	 * Construct a download manager. Creates the gui and initializes lists.
	 */
	private DownloadManager() {
		mActiveList = new ArrayList<DownloadObject>();
		mInactiveList = new ArrayList<DownloadObject>();
		mPendingList = new ArrayList<DownloadObject>();
		mCompletedList = new ArrayList<DownloadObject>();
		mErrorList = new ArrayList<DownloadObject>();
	}

	/**
	 * Tries to add a download object to the active list.
	 * Will not work if the max number of downloads is already reached.
	 */
	public boolean addToActiveList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Tries to add a download object to the inactive list.
	 */
	public boolean addToInactiveList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Tries to add a download object to the pending list.
	 */
	public boolean addToPendingList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Tries to add a download object to the completed list.
	 */
	public boolean addToCompletedList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Tries to add a download object to the error list.
	 */
	public boolean addToErrorList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes a download from the active list.
	 */
	public void removeFromActiveList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes a download from the inactive list.
	 */
	public void removeFromInactiveList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes a download from the pending list.
	 */
	public void removeFromPendingList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes a download from the completed list.
	 */
	public void removeFromCompletedList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes a download from the error list.
	 */
	public void removeFromErrorList(DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Add a download to the download manager.
	 * @param URL The url at which the download is located.
	 */
	public DownloadObject addDownload(String URL) throws MalformedURLException {
		boolean valid = true; // if url is valid;
		if (!valid) {
			throw new MalformedURLException("The URL is not a valid URL");
		}
		DownloadObject downloadObject = new DownloadObject("C:/downloads/textFile.txt", new HTTPDownloadConnection(URL));
		downloadObject.addListener(this);
		return downloadObject;
	}

	/**
	 * Tries to start downloading all download objects in the selected list.
	 */
	public void startDownloading() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Tries to stop downloading all download objects in the selected list.
	 */
	public void stopDownloading() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @see DownloadObserver
	 */
	public void downloadEventPerformed(DownloadEvent downloadEvent) {
	}
}
