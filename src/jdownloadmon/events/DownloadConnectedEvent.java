package jdownloadmon.events;

import jdownloadmon.DownloadObject;

/**
 * A class for the event when a download has successfully connected to the server.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadConnectedEvent extends DownloadEvent {

	/**
	 * Construct a download connected event.
	 * @param downloadObject The download object.
	 */
	public DownloadConnectedEvent(DownloadObject downloadObject) {
		super(downloadObject);
	}
}
