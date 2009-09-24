package downloadmanager;

/**
 * A download progress event contains information about the percent downloaded of a {@link DownloadObject}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadProgressEvent extends DownloadEvent {
	
	/** The percent (0-100) downloaded. */
	private int mPercentDownloaded;

	/**
	 * Construct a download event.
	 * @param downloadObject The download object asoociated with this event.
	 */
	public DownloadProgressEvent(DownloadObject downloadObject) {
		super(downloadObject);
		mPercentDownloaded = downloadObject.getPercentDownloaded();
	}

	/**
	 * Get the snapshot of how many percent were downloaded when this event was created.
	 * @return The percentage as an intger representing how much was downloaded at the time of this event's creation.
	 */
	public int getPercentDownloaded() {
		return mPercentDownloaded;
	}
}
