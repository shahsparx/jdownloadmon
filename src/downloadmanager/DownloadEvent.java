package downloadmanager;

/**
 * A download event contains information about the state and percent downloaded of a {@link DownloadObject}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadEvent {

    /** The download object that raised this event. */
    private DownloadObject mDownloadObject;
    /** The percent (0-100) downloaded. */
    private int mPercentDownloaded;
    /** The state the download object was at at the time of this event's creation. */
    private StatusState mState;

    /**
     * Construct a download event.
     * @param downloadObject The download object asoociated with this event.
     */
    public DownloadEvent(DownloadObject downloadObject) {
	mDownloadObject = downloadObject;
	mPercentDownloaded = downloadObject.getPercentDownloaded();
	mState = downloadObject.getStatusState();
    }

    /**
     * Get the object that is being downloaded.
     * @return The download object that this event is associated with.
     */
    public DownloadObject getDownloadObject() {
	return mDownloadObject;
    }

    /**
     * Get the snapshot of how many percent were downloaded when this event was created.
     * @return The percentage as an intger representing how much was downloaded at the time of this event's creation.
     */
    public int getPercentDownloaded() {
	return mPercentDownloaded;
    }

    /**
     * Get the snapshot of the state when the event was created.
     * @return The state that was the state of the object when this event was created.
     */
    public StatusState getState() {
	return mState;
    }
}
