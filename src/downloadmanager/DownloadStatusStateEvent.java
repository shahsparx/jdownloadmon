package downloadmanager;

/**
 * A download progress event contains information about the status state of a {@link DownloadObject}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadStatusStateEvent extends DownloadEvent {

	/** The status state the download object was at at the time of this event's creation. */
	private StatusState mStatusState;

	/**
	 * Construct a download status state event.
	 * @param downloadObject The download object asoociated with this event.
	 */
	public DownloadStatusStateEvent(DownloadObject downloadObject) {
		super(downloadObject);
		mStatusState = downloadObject.getStatusState();
	}

	/**
	 * Get the snapshot of the status state when the event was created.
	 * @return The status state that was the state of the object when this event was created.
	 */
	public StatusState getStatusState() {
		return mStatusState;
	}

}
