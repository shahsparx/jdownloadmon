package jdownloadmon.events;

import jdownloadmon.DownloadObject;

/**
 * A download progress event contains some information about a {@link DownloadObject}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class DownloadEvent {

	/** The download object that raised this event. */
	protected DownloadObject mDownloadObject;

	/**
	 * Construct a download event.
	 * @param downloadObject The download object asoociated with this event.
	 */
	protected DownloadEvent(DownloadObject downloadObject) {
		mDownloadObject = downloadObject;
	}

	/**
	 * Get the object that is being downloaded.
	 * @return The download object that this event is associated with.
	 */
	public DownloadObject getDownloadObject() {
		return mDownloadObject;
	}
}
