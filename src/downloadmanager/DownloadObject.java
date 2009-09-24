package downloadmanager;

import java.util.ArrayList;

/**
 * A download object represents anything that is being downloaded.
 * The class contains methods for connecting to and downloading files among other things.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadObject implements Runnable, DownloadProgressObservable, DownloadStatusStateObservable {

	/** List of progress observers observing this download object. */
	private ArrayList<DownloadProgressObserver> mProgressObservers;
	/** List of status state observers observing this download object. */
	private ArrayList<DownloadStatusStateObserver> mStatusStateObservers;
	/** The download file's destionation. */
	private String mDestination;
	/** The current downloaded size, in bytes. */
	private long mDownloadedSize;
	/** The size of the complete file. */
	private long mSize;
	/** The current state of this download object. */
	private StatusState mStatusState;
	/** The connection to the server. */
	private DownloadConnection mDownloadConnection;

	/**
	 * Construct a download object.
	 * @param destination Where to put the file on the drive.
	 * @param connection The download connection to use for downloading.
	 */
	public DownloadObject(String destination, DownloadConnection connection) {
		mDestination = destination;
		mDownloadConnection = connection;
		mProgressObservers = new ArrayList<DownloadProgressObserver>();
		mStatusStateObservers = new ArrayList<DownloadStatusStateObserver>();
		mStatusState = new InactiveState(this);
	}

	/**
	 * @see Runnable#run()
	 */
	public void run() {
		throw new UnsupportedOperationException("Not supported yet.");
	}



	/**
	 * Get the current state of the download object.
	 * @return the {@link StatusState} that this object is currently in.
	 */
	public StatusState getStatusState() {
		return mStatusState;
	}

	/**
	 * Get the current download percentage.
	 * @return an <tt>integer</tt> value representing how far this download has gotten percentually.
	 */
	public int getPercentDownloaded() {
		return 0;
	}

	/**
	 * Change the state of the download object.
	 * @param state The StatusState to change to.
	 */
	public void setStatusState(StatusState state) {
		mStatusState.setStatusState(state, this);
	}

	/**
	 * Get the destination.
	 * @return Destination file path, as a String.
	 */
	public String getDestination() {
		return mDestination;
	}

	/**
	 * Set the destination.
	 * Will have to stop downloading first for it to work.
	 * @param newDestination The destination file path, as a String.
	 */
	public void setDestination(String newDestination) throws AlreadyDownloadingException {
		if (!(mStatusState instanceof ActiveState)) {
			mDestination = newDestination;
		} else {
			throw new AlreadyDownloadingException("Cannot change destination, already downloading!");
		}
	}

	/**
	 * Get the download connection of this download object.
	 * @return The download connection of this download object.
	 */
	public DownloadConnection getConnection() {
		return mDownloadConnection;
	}

	/**
	 * Set the connection to a new connection.
	 * Will have to stop downloading first for it to work.
	 * @param downloadConnection The new connection to set to.
	 */
	public void setConnection(DownloadConnection downloadConnection) throws AlreadyDownloadingException {
		if (!(mStatusState instanceof ActiveState)) {
			mDownloadConnection = downloadConnection;
		} else {
			throw new AlreadyDownloadingException("Cannot change connection, already downloading!");
		}
	}

	/**
	 * @see DownloadProgressObservable#addProgressListener(DownloadProgressObserver observer)
	 */
	public void addProgressListener(DownloadProgressObserver observer) {
		mProgressObservers.add(observer);
	}

	/**
	 * @see DownloadProgressObservable#removeProgressListener(DownloadProgressObserver observer)
	 */
	public void removeProgressListener(DownloadProgressObserver observer) {
		mProgressObservers.remove(observer);
	}

	/**
	 * @see DownloadProgressObservable#notifyProgressListeners(DownloadProgressEvent downloadProgressEvent)
	 */
	public void notifyProgressListeners(DownloadProgressEvent downloadProgressEvent) {
		for(DownloadProgressObserver observer : mProgressObservers) {
			observer.downloadProgressEventPerformed(downloadProgressEvent);
		}
	}

	/**
	 * @see DownloadStatusStateObservable#addProgressListener(DownloadProgressObserver observer)
	 */
	public void addStatusStateListener(DownloadStatusStateObserver observer) {
		mStatusStateObservers.add(observer);
	}

	/**
	 * @see DownloadStatusStateObservable#removeProgressListener(DownloadProgressObserver observer)
	 */
	public void removeStatusStateListener(DownloadStatusStateObserver observer) {
		mStatusStateObservers.remove(observer);
	}

	/**
	 * @see DownloadStatusStateObservable#notifyProgressListeners(DownloadProgressEvent downloadProgressEvent)
	 */
	public void notifyStatusStateListeners(DownloadStatusStateEvent downloadStatusStateEvent) {
		for(DownloadStatusStateObserver observer : mStatusStateObservers) {
			observer.downloadStatusStateEventPerformed(downloadStatusStateEvent);
		}
	}
}
