package downloadmanager.events;

import downloadmanager.states.StatusState;
import downloadmanager.*;

/**
 * A download progress event contains information about the status state of a {@link DownloadObject}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadStatusStateEvent extends DownloadEvent {

	/** The status state the download object was at at the time of this event's creation. */
	private StatusState mStatusState;
	/** The status state the download is changing to. */
	private StatusState mNewStatusState;

	/**
	 * Construct a download status state event.
	 * @param newStatusState The new status state the download object wants to change to.
	 */
	public DownloadStatusStateEvent(StatusState newStatusState) {
		super(newStatusState.getDownloadObject());
		mStatusState = mDownloadObject.getStatusState();
		mNewStatusState = newStatusState;
	}

	/**
	 * Get the snapshot of the status state when the event was created.
	 * @return The status state that was the state of the object when this event was created.
	 */
	public StatusState getStatusState() {
		return mStatusState;
	}

	/**
	 * @return the state the download is changing to.
	 */
	public StatusState getNewStatusState() {
		return mNewStatusState;
	}

}
