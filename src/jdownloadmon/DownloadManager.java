package jdownloadmon;

import java.io.File;
import jdownloadmon.events.DownloadConnectedEvent;
import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.events.DownloadProgressEvent;
import jdownloadmon.states.ActiveState;
import jdownloadmon.states.CompletedState;
import jdownloadmon.states.PendingState;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JFileChooser;

/**
 * The download manager keeps track of download objects and moves them around its lists.
 * [singleton]
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadManager implements DownloadObserver {

	/** The singleton instance of this DownloadManager. */
	public static final DownloadManager INSTANCE = new DownloadManager();
	/** The config file with the settings. */
	private XMLConfigFile mConfigFile;
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
	/** List of URLs in the manager. */
	private ArrayList<URL> mURLs;

	/**
	 * Enum for default file exist behaviors.
	 */
	public enum DefaultFileExistsBehavior {

		/** Resume the file. */
		RESUME,
		/** Rename the file. */
		RENAME,
		/** Replace the file. */
		REPLACE;
	}

	/**
	 * Construct a download manager. Initializes lists.
	 */
	private DownloadManager() {
		mActiveList = new ArrayList<DownloadObject>();
		mInactiveList = new ArrayList<DownloadObject>();
		mPendingList = new ArrayList<DownloadObject>();
		mCompletedList = new ArrayList<DownloadObject>();
		mErrorList = new ArrayList<DownloadObject>();
		mURLs = new ArrayList<URL>();
	}

	/**
	 * Initialize settings.
	 */
	public void init() {
		XMLConfigFile file = null;
		try {
			file = XMLConfigFile.loadFile();
			if (file == null) {
				// If the config.xml file does not exist, these settings will be used and written to that file.
				int maxDownloads = 3;
				DefaultFileExistsBehavior defaultFileExistsBehavior = DefaultFileExistsBehavior.RESUME;
				String defaultDirectory = new JFileChooser().getFileSystemView().getDefaultDirectory() + File.separator + "Downloads";
				file = new XMLConfigFile(defaultDirectory, maxDownloads, defaultFileExistsBehavior);
				file.saveFile();
			}
		} catch (Exception e) {
			DownloadLogger.LOGGER.log(Level.SEVERE, e.toString());
		}

		mConfigFile = file;
	}

	/**
	 * Save settings
	 * @param configFile The xml config file with the settings.
	 */
	public void saveSettings(XMLConfigFile configFile) {
		mConfigFile = configFile;
		try {
			mConfigFile.saveFile();
		} catch (Exception e) {
			DownloadLogger.LOGGER.log(Level.SEVERE, e.toString());
		}
	}

	/**
	 * @return The xml config file with the settings of this download manager.
	 */
	public XMLConfigFile getSettings() {
		return mConfigFile;
	}

	/**
	 * Tries to add a download object to the active list.
	 * Will not work if the max number of downloads is already reached.
	 * @param downloadObject The download object to be added to the list.
	 * @return <tt>true</tt> if the download could be added, <tt>false</tt> otherwise.
	 */
	public boolean addToActiveList(DownloadObject downloadObject) {
		int maxDownloads = mConfigFile.getMaxDownloads();
		if (maxDownloads == 0 || mActiveList.size() < maxDownloads) {
			return mActiveList.add(downloadObject);
		}

		return false;
	}

	/**
	 * @return The default directory for new downloads.
	 */
	public String getDefaultDirectory() {
		return mConfigFile.getDefaultDirectory();
	}

	/**
	 * Tries to add a download object to the inactive list.
	 * @param downloadObject The download object to be added to the list.
	 * @return <tt>true</tt> if the download could be added, <tt>false</tt> otherwise.
	 */
	public boolean addToInactiveList(DownloadObject downloadObject) {
		return mInactiveList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the pending list.
	 * @param downloadObject The download object to be added to the list.
	 * @return <tt>true</tt> if the download could be added, <tt>false</tt> otherwise.
	 */
	public boolean addToPendingList(DownloadObject downloadObject) {
		return mPendingList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the completed list.
	 * @param downloadObject The download object to be added to the list.
	 * @return <tt>true</tt> if the download could be added, <tt>false</tt> otherwise.
	 */
	public boolean addToCompletedList(DownloadObject downloadObject) {
		return mCompletedList.add(downloadObject);
	}

	/**
	 * Tries to add a download object to the error list.
	 * @param downloadObject The download object to be added to the list.
	 * @return <tt>true</tt> if the download could be added, <tt>false</tt> otherwise.
	 */
	public boolean addToErrorList(DownloadObject downloadObject) {
		return mErrorList.add(downloadObject);
	}

	/**
	 * Removes a download from the active list.
	 * @param downloadObject The download object to be removed from the list.
	 */
	public void removeFromActiveList(DownloadObject downloadObject) {
		mActiveList.remove(downloadObject);
	}

	/**
	 * Removes a download from the inactive list.
	 * @param downloadObject The download object to be removed from the list.
	 */
	public void removeFromInactiveList(DownloadObject downloadObject) {
		mInactiveList.remove(downloadObject);
	}

	/**
	 * Removes a download from the pending list.
	 * @param downloadObject The download object to be removed from the list.
	 */
	public void removeFromPendingList(DownloadObject downloadObject) {
		mPendingList.remove(downloadObject);
	}

	/**
	 * Removes a download from the completed list.
	 * @param downloadObject The download object to be removed from the list.
	 */
	public void removeFromCompletedList(DownloadObject downloadObject) {
		mCompletedList.remove(downloadObject);
	}

	/**
	 * Removes a download from the error list.
	 * @param downloadObject The download object to be removed from the list.
	 */
	public void removeFromErrorList(DownloadObject downloadObject) {
		mErrorList.remove(downloadObject);
	}

	/**
	 * Add a download to the download manager.
	 * @param downloadObject The object to add.
	 * @return The download object that was added.
	 * @throws MalformedURLException if the URL is not a valid URL.
	 * @throws URLAlreadyExistsException if the URL already exists in the download manager in some other download object.
	 */
	public DownloadObject addDownload(DownloadObject downloadObject) throws MalformedURLException, URLAlreadyExistsException {
		URL verifiedURL = downloadObject.getConnection().getURL();
		for (URL url : mURLs) {
			if (url.equals(verifiedURL)) {
				throw new URLAlreadyExistsException("This URL already exists.");
			}
		}

		mURLs.add(verifiedURL);
		downloadObject.addListener(this);
		mInactiveList.add(downloadObject);
		saveDownloadsFile();

		return downloadObject;
	}

	/**
	 * Add a download to the download manager.
	 * @param URLString The url at which the download is located.
	 * @param directory The directory to download to.
	 * @return The download object that was added.
	 * @throws MalformedURLException if the URL is not a valid URL.
	 * @throws URLAlreadyExistsException if the URL already exists in the download manager in some other download object.
	 */
	public DownloadObject addDownload(String URLString, String directory)
			throws MalformedURLException, URLAlreadyExistsException {
		DownloadObject downloadObject = createDownloadObject(URLString, directory);
		return addDownload(downloadObject);
	}

	/**
	 * Create a new download object.
	 * @param URLString The url at which the download is located.
	 * @param directory The directory to download to.
	 * @return The download object that was added.
	 * @throws MalformedURLException if the URL is not a valid URL.
	 */
	public DownloadObject createDownloadObject(String URLString, String directory) throws MalformedURLException {
		URL verifiedURL = verifyUrl(URLString);
		DownloadObject downloadObject = new DownloadObject(new HTTPDownloadConnection(verifiedURL));
		downloadObject.setDirectory(directory);
		return downloadObject;
	}

	/**
	 * Create a new download object.
	 * @param URLString The url at which the download is located.
	 * @param path The full path (directory+filename+extension) to download to.
	 * @return The download object that was added.
	 * @throws MalformedURLException if the URL is not a valid URL.
	 */
	public DownloadObject createDownloadObjectWithPath(String URLString, String path) throws MalformedURLException {
		URL verifiedURL = verifyUrl(URLString);
		DownloadObject downloadObject = new DownloadObject(new HTTPDownloadConnection(verifiedURL));
		downloadObject.setPath(path);
		return downloadObject;
	}

	/**
	 * Save downloads file.
	 * Synchronized to avoid potential file write errors across multiple threads.
	 */
	private synchronized void saveDownloadsFile() {
		try {
			ArrayList<DownloadObject> allDownloads = new ArrayList<DownloadObject>();
			allDownloads.addAll(mActiveList);
			allDownloads.addAll(mInactiveList);
			allDownloads.addAll(mPendingList);
			allDownloads.addAll(mCompletedList);
			allDownloads.addAll(mErrorList);

			new XMLDownloadsFile(allDownloads).saveFile();
		} catch (Exception e) {
			DownloadLogger.LOGGER.severe(e.toString());
		}
	}

	/**
	 * Verify an URL.
	 * @param url The String to verify as an url.
	 * @return A new URL.
	 * @throws MalformedURLException if the URL is not a valid URL.
	 */
	private URL verifyUrl(String url) throws MalformedURLException {
		// Only allow HTTP URLs.
		if (!url.toLowerCase().startsWith("http://")) {
			throw new MalformedURLException("Must start with http://");
		}

		URL verifiedUrl = new URL(url);
		// Make sure URL specifies a file.
		if (verifiedUrl.getFile().length() < 2) {
			throw new MalformedURLException("URL has to specify a file.");
		}
		return verifiedUrl;
	}

	public void downloadEventPerformed(DownloadProgressEvent downloadProgressEvent) {
		if (downloadProgressEvent.getPercentDownloaded() == 100) {
			DownloadObject downloadObject = downloadProgressEvent.getDownloadObject();
			downloadObject.changeStatusState(new CompletedState(downloadObject));
		}
	}

	public void downloadEventPerformed(DownloadStatusStateEvent downloadStatusStateEvent) {
		updatePendingList();
	}

	public void downloadEventPerformed(DownloadConnectedEvent downloadConnectedEvent) {
		saveDownloadsFile();
	}

	/**
	 * Check if there are pending downloads and if so move the top one up to the active list.
	 */
	private void updatePendingList() {
		// if an active download has stopped downloading, activate top pending download.
		while (mPendingList.size() > 0 && mActiveList.size() < mConfigFile.getMaxDownloads()) {
			DownloadObject pending = mPendingList.get(0);
			pending.changeStatusState(new ActiveState(pending));
		}
	}

	/**
	 * Get the queue position of an active download.
	 * @param downloadObject The download object to get the queue position of.
	 * @return the download's queue position.
	 */
	public int getActiveQueuePosition(DownloadObject downloadObject) {
		return mActiveList.indexOf(downloadObject) + 1;
	}

	/**
	 * Get the queue position of a pending download.
	 * @param downloadObject The download object to get the queue position of.
	 * @return the download's queue position.
	 */
	public int getPendingQueuePosition(DownloadObject downloadObject) {
		return mActiveList.size() + mPendingList.indexOf(downloadObject) + 1;
	}

	/**
	 * @return The number of active and pending downloads.
	 */
	public int getNumberOfQueuedDownloads() {
		return mActiveList.size() + mPendingList.size();
	}

	/**
	 * Move an active download up the active queue.
	 * @param downloadObject The download object to move.
	 */
	public void moveActiveUp(DownloadObject downloadObject) {
		int index = mActiveList.indexOf(downloadObject);
		if (index != -1 && index != 0) {
			DownloadObject d = mActiveList.get(index - 1);
			mActiveList.set(index - 1, downloadObject);
			mActiveList.set(index, d);
		}
	}

	/**
	 * Move a pending download down the pending queue.
	 * @param downloadObject The download object to move.
	 */
	public void movePendingDown(DownloadObject downloadObject) {
		int index = mPendingList.indexOf(downloadObject);
		if (index != -1 && index != mPendingList.size() - 1) {
			DownloadObject d = mPendingList.get(index + 1);
			mPendingList.set(index + 1, downloadObject);
			mPendingList.set(index, d);
		}
	}

	/**
	 * Move a pending download up the pending queue,
	 * or up to the active queue if it's at the top of the pending queue.
	 * @param downloadObject The download object to move.
	 */
	public void movePendingUp(DownloadObject downloadObject) {
		int index = mPendingList.indexOf(downloadObject);
		if (index != -1) {
			if (index == 0) {
				switchBottomActiveWithTopPending();
			} else {
				DownloadObject d = mPendingList.get(index - 1);
				mPendingList.set(index - 1, downloadObject);
				mPendingList.set(index, d);
			}
		}
	}

	/**
	 * Move an active download down the active queue,
	 * or down to the pending queue if it's at the bottom of the active queue.
	 * @param downloadObject The download object to move.
	 */
	public void moveActiveDown(DownloadObject downloadObject) {
		int index = mActiveList.indexOf(downloadObject);
		if (index != -1) {
			if (index == mActiveList.size() - 1) {
				if (mPendingList.size() > 0) {
					switchBottomActiveWithTopPending();
				}
			} else {
				DownloadObject d = mActiveList.get(index + 1);
				mActiveList.set(index + 1, downloadObject);
				mActiveList.set(index, d);
			}
		}
	}

	/**
	 * Switch the bottommost active download object with the topmost pending download object.
	 */
	private void switchBottomActiveWithTopPending() {
		// move active first by setting it to pending.
		DownloadObject bottomActive = mActiveList.get(mActiveList.size() - 1);
		bottomActive.changeStatusState(new PendingState(bottomActive));
		// now the newly pending download will be added to the bottom of the pending queue,
		// as per the setStatusState method, so insert it at the top of the pending list.
		DownloadObject bottomPending = mPendingList.get(mPendingList.size() - 1);
		mPendingList.add(0, bottomPending);
		// Now there're two of the same, so remove the one left at the bottom.
		mPendingList.remove(mPendingList.size() - 1);
	}

	/**
	 * Rmove a download from the download manager.
	 * Actually only removes the URL from the manager's list of URLs.
	 * @param downloadObject Which download object to remove.
	 */
	public void removeDownload(DownloadObject downloadObject) {
		mURLs.remove(downloadObject.getConnection().getURL());
		saveDownloadsFile();
	}
}
