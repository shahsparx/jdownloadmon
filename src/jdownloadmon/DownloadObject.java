package jdownloadmon;

import java.io.File;
import jdownloadmon.events.DownloadConnectedEvent;
import jdownloadmon.states.ActiveState;
import jdownloadmon.states.StatusState;
import jdownloadmon.states.ErrorState;
import jdownloadmon.states.InactiveState;
import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.events.DownloadProgressEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

/**
 * A download object represents anything that is being downloaded.
 * The class contains methods for connecting to and downloading files among other things.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadObject implements Runnable, DownloadObservable {

	/** Buffer size. */
	private static final int BUFFER_SIZE = 1024;
	/** How long to wait between each update. */
	private static final int UPDATE_INTERVAL_MILLISECONDS = 1000;
	/** List of progress observers observing this download object. */
	private ArrayList<DownloadObserver> mObservers;
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
	/** File handler to write and read files on system. */
	private DownloadFile mDownloadFile;
	/** Keeping track of bytes/second. */
	private double mSpeed;
	/** Keeping track of ETA. */
	private long mETA;

	/**
	 * Construct a download object.
	 * @param connection The download connection to use for downloading.
	 */
	public DownloadObject(DownloadConnection connection) {
		mDownloadConnection = connection;
		mObservers = new ArrayList<DownloadObserver>();
		mStatusState = new InactiveState(this);
	}

	/**
	 * Set the directory.
	 * @param directory The directory to set to.
	 * @throws IllegalStateException if the download object is currently downloading.
	 */
	public void setDirectory(String directory) throws IllegalStateException {
		mDestination = directory + File.separator + mDownloadConnection.getFileName();
	}

	/**
	 * Set the full path to the file.
	 * @param path The path to set to.
	 */
	public void setPath(String path) {
		mDestination = path;
	}

	/**
	 * @return The downloaded size in bytes, as a long.
	 */
	public long getDownloadedSize() {
		return mDownloadedSize;
	}

	/**
	 * Try to connect to server and gradually retrieve the file.
	 * Synchronized to avoid multiple threads writing to the same file simultaneously, causing write error
	 * (i.e making sure the thread that's about to stop accessing a file closes it completely before another opens the same file).
	 * @see Runnable#run()
	 */
	public synchronized void run() {
		DownloadConnection runConnection = null;
		Timer timer = new Timer();
		try {
			// Get a copy of the connection and connect anew to avoid getting the wrong stream or any such strange behaviors.
			runConnection = mDownloadConnection.getDeepCopy();
			mDownloadFile = new DownloadFile(mDestination, mDownloadedSize);
			mDestination = mDownloadFile.getDestination();
			mDownloadedSize = DownloadFile.getFileLength(mDestination);
			mSize = runConnection.connect(mDownloadedSize);
			notifyListeners(new DownloadConnectedEvent(this));
			/**
			 * Create a new TimerTask that will run (on its own thread) at set intervals.
			 * Used for updating the speed and ETA and also notifying listeners about download progress events.
			 */
			TimerTask task = new TimerTask() {

				/** How much was downloaded last time. */
				private long iMLastDownloadedSize = mDownloadedSize;
				/** The nanoTime last time. */
				private long iMLastTime = System.nanoTime();

				@Override
				public void run() {
					long timeElapsedSinceLastTime = System.nanoTime() - iMLastTime;
					iMLastTime = System.nanoTime();
					// Difference between last time and this time = how much was downloaded since last run.
					long downloadedSinceLastTime = mDownloadedSize - iMLastDownloadedSize;
					iMLastDownloadedSize = mDownloadedSize;
					if (timeElapsedSinceLastTime > 0) {
						// Speed (bytes per second) = downloaded bytes / time in seconds (nanoseconds / 1000000000)
						mSpeed = downloadedSinceLastTime * 1000000000.0 / timeElapsedSinceLastTime;
					}

					if (mSpeed > 0) {
						// ETA (milliseconds) = remaining byte size / bytes per millisecond (bytes per second * 1000)
						mETA = (mSize - mDownloadedSize) * 1000 / (long) mSpeed;
					} else {
						mETA = 0;
					}

					notifyListeners(new DownloadProgressEvent(DownloadObject.this));
				}
			};
			// Schedule above task for every (UPDATE_INTERVAL_MILLISECONDS) milliseconds.
			timer.schedule(task, UPDATE_INTERVAL_MILLISECONDS, UPDATE_INTERVAL_MILLISECONDS);
			// Download bytes while bytes need downloading. 'Nuff said.
			while (mStatusState instanceof ActiveState && mDownloadedSize < mSize) {
				byte[] bytes = runConnection.getBytes(mDownloadedSize, mSize, BUFFER_SIZE);
				mDownloadFile.write(bytes);
				mDownloadedSize += bytes.length;
			}
			mSpeed = 0;
			mETA = 0;
			// Notify listeners about 100% progress.
			notifyListeners(new DownloadProgressEvent(this));
		} catch (NullPointerException ex) {
			// If there's a null pointer exception it's probably spit out when closing the application
			// during a download process so don't leave it in the log.
			if (!Constants.RELEASE) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			changeStatusState(new ErrorState(this, ex.toString()));
			if (!Constants.RELEASE) {
				ex.printStackTrace();
			}

			DownloadLogger.LOGGER.log(Level.WARNING, ex.toString());
		} finally {
			// stop updating the download.
			timer.cancel();
			// close the file and connection.
			if (mDownloadFile != null) {
				mDownloadFile.close();
			}

			if (runConnection != null) {
				runConnection.close();
			}
		}
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
		if (mSize == 0) {
			return 0;
		}

		return (int) (mDownloadedSize * 100 / mSize);
	}

	/**
	 * Change the status state of this download object.
	 * @param state The status state to change to.
	 */
	public void setStatusState(StatusState state) {
		mStatusState = state;
	}

	/**
	 * Change the state of the download object and fire any events associated with the change.
	 * @param state The status state to change to.
	 */
	public void changeStatusState(StatusState state) {
		mStatusState.changeTo(state);
	}

	/**
	 * Get the destination.
	 * @return Destination file path, as a String.
	 */
	public String getDestination() {
		return mDestination;
	}

	/**
	 * Get the download connection of this download object.
	 * @return The download connection of this download object.
	 */
	public DownloadConnection getConnection() {
		return mDownloadConnection;
	}

	public void addListener(DownloadObserver observer) {
		mObservers.add(observer);
	}

	public void removeListener(DownloadObserver observer) {
		mObservers.remove(observer);
	}

	public void notifyListeners(DownloadProgressEvent downloadProgressEvent) {
		for (DownloadObserver observer : mObservers) {
			observer.downloadEventPerformed(downloadProgressEvent);
		}
	}

	public void notifyListeners(DownloadStatusStateEvent downloadStatusStateEvent) {
		for (DownloadObserver observer : mObservers) {
			observer.downloadEventPerformed(downloadStatusStateEvent);
		}
	}

	public void notifyListeners(DownloadConnectedEvent downloadConnectedEvent) {
		for (DownloadObserver observer : mObservers) {
			observer.downloadEventPerformed(downloadConnectedEvent);
		}
	}

	/**
	 * Try to pause this download object's download.
	 */
	public void pause() {
		mStatusState.pause();
	}

	/**
	 * Try to start this download.
	 */
	public void download() {
		mStatusState.download();
	}

	/**
	 * Remove this download.
	 */
	public void remove() {
		pause();
		mStatusState.remove();
		if (mDownloadFile != null && (mDownloadedSize == 0 || mDownloadedSize < mSize)) {
			mDownloadFile.close();
			mDownloadFile = null;
			new File(mDestination).delete();
		}
	}

	/**
	 * @return The queue position of this download.
	 */
	public int getQueuePosition() {
		return mStatusState.getQueuePosition();
	}

	/**
	 * @return Total size of this download object, in bytes.
	 */
	public long getSize() {
		return mSize;
	}

	/**
	 * Set the total size.
	 * @param size The size to set to.
	 */
	public void setSize(long size) {
		mSize = size;
	}

	/**
	 * Set the downloaded size.
	 * @param downloaded The size to set to.
	 */
	public void setDownloadedSize(long downloaded) {
		mDownloadedSize = downloaded;
	}

	/**
	 * @return The rate this download object is downloading, in bytes per second.
	 */
	public double getSpeed() {
		return mSpeed;
	}

	/**
	 * @return The estimated time of arrival, in milliseconds.
	 */
	public long getETA() {
		return mETA;
	}

	/**
	 * Open the file that is at the destination of this download object.
	 */
	public void openFile() {
		mStatusState.openFile();
	}
}
