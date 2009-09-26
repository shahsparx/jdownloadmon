package downloadmanager;

import downloadmanager.events.DownloadStatusStateEvent;
import downloadmanager.events.DownloadProgressEvent;
import downloadmanager.states.CompletedState;
import java.net.URL;
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
		if (mActiveList.size() >= mMaxDownloads) {
			return false;
		}

		mActiveList.add(downloadObject);
		return true;
	}

	/**
	 * Tries to add a download object to the inactive list.
	 */
	public boolean addToInactiveList(DownloadObject downloadObject) {
		return mInactiveList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the pending list.
	 */
	public boolean addToPendingList(DownloadObject downloadObject) {
	return mPendingList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the completed list.
	 */
	public boolean addToCompletedList(DownloadObject downloadObject) {
		return mCompletedList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the error list.
	 */
	public boolean addToErrorList(DownloadObject downloadObject) {
		return mErrorList.add(downloadObject);
	}

	/**
	 * Removes a download from the active list.
	 */
	public void removeFromActiveList(DownloadObject downloadObject) {
		mActiveList.remove(downloadObject);
	}

	/**
	 * Removes a download from the inactive list.
	 */
	public void removeFromInactiveList(DownloadObject downloadObject) {
		mInactiveList.remove(downloadObject);
	}

	/**
	 * Removes a download from the pending list.
	 */
	public void removeFromPendingList(DownloadObject downloadObject) {
		mPendingList.remove(downloadObject);
	}

	/**
	 * Removes a download from the completed list.
	 */
	public void removeFromCompletedList(DownloadObject downloadObject) {
		mCompletedList.remove(downloadObject);
	}

	/**
	 * Removes a download from the error list.
	 */
	public void removeFromErrorList(DownloadObject downloadObject) {
		mErrorList.remove(downloadObject);
	}

	/**
	 * Add a download to the download manager.
	 * @param URL The url at which the download is located.
	 */
	public DownloadObject addDownload(String URL) throws MalformedURLException {
		URL verifiedURL = verifyUrl(URL);
		if (verifiedURL == null) {
			throw new MalformedURLException("The URL is not a valid URL");
		}
		DownloadObject downloadObject = new DownloadObject(new HTTPDownloadConnection(verifiedURL));
		mInactiveList.add(downloadObject);
		downloadObject.addListener(this);
		return downloadObject;
	}

	private URL verifyUrl(String url) {
		// Only allow HTTP URLs.
		if (!url.toLowerCase().startsWith("http://")) {
			return null;
		}
		// Verify format of URL.
		URL verifiedUrl = null;
		try {
			verifiedUrl = new URL(url);
		} catch (Exception e) {
			return null;
		}
		// Make sure URL specifies a file.
		if (verifiedUrl.getFile().length() < 2) {
			return null;
		}
		return verifiedUrl;
	}

	public void downloadEventPerformed(DownloadProgressEvent downloadProgressEvent) {
		if (downloadProgressEvent.getPercentDownloaded() == 100) {
			DownloadObject downloadObject = downloadProgressEvent.getDownloadObject();
			DownloadStatusStateEvent statusStateEvent = new DownloadStatusStateEvent(new CompletedState(downloadObject));
			downloadObject.notifyListeners(statusStateEvent);
		}
	}

	public void downloadEventPerformed(DownloadStatusStateEvent downloadStatusStateEvent) {
		DownloadObject downloadObject = downloadStatusStateEvent.getDownloadObject();
		downloadObject.setStatusState(downloadStatusStateEvent.getNewStatusState());
	}
}
