package jdownloadmon.states;

import jdownloadmon.*;
import jdownloadmon.events.DownloadStatusStateEvent;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * A StatusState is a state which a {@link DownloadObject} uses to perform certain state-based actions.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class StatusState {

	/** The download object this state is associated with. */
	protected DownloadObject mDownloadObject;

	/**
	 * Construct a status state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	protected StatusState(DownloadObject downloadObject) {
		mDownloadObject = downloadObject;
	}

	/**
	 * @return a shallow copy of this status state.
	 */
	public abstract StatusState getShallowCopy();

	/**
	 * @return The download object wrapped in this statusState.
	 */
	public DownloadObject getDownloadObject() {
		return mDownloadObject;
	}

	/**
	 * Try to start downloading.
	 */
	public abstract void download();

	/**
	 * Open the file that is at the destination of the wrapped download object.
	 */
	public void openFile() {
		File file = new File(mDownloadObject.getDestination());
		if (Desktop.isDesktopSupported() && file.exists()) {
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException ex) {
				DownloadLogger.LOGGER.log(Level.WARNING, ex.toString());
			}
		}
	}

	/**
	 * Try to pause downloading.
	 */
	public abstract void pause();

	/**
	 * Try to change a download object's state from this status state to another.
	 * @param state The status state to change to.
	 */
	public void changeTo(StatusState state) {
		StatusState oldState = mDownloadObject.getStatusState();
		if (mDownloadObject != null && state.changeTo()) {
			DownloadStatusStateEvent event = new DownloadStatusStateEvent(mDownloadObject);
			mDownloadObject.notifyListeners(event);
			oldState.changeFrom();
		}
	}

	/**
	 * Change from this state.
	 */
	public abstract void changeFrom();

	/**
	 * Change to this state.
	 * @return <tt>true</tt> the change was successful, <tt>false</tt> otherwise.
	 */
	public abstract boolean changeTo();

	/**
	 * Remove a download object from the download manager.
	 */
	public void remove() {
		changeFrom();
	}

	/**
	 * @return The download object's queue position, or an empty String if the download is not queuing.
	 */
	public abstract int getQueuePosition();
}
