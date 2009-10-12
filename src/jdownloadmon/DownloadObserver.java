package jdownloadmon;

import jdownloadmon.events.DownloadConnectedEvent;
import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.events.DownloadProgressEvent;

/**
 * Download progress observer interface for observers of download observables.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadObserver {

	/**
	 * A download progress event was performed.
	 * @param downloadProgressEvent The download progress event that was performed.
	 */
	public void downloadEventPerformed(DownloadProgressEvent downloadProgressEvent);

	/**
	 * A download status state event was performed.
	 * @param downloadStatusStateEvent The download status state event that was performed.
	 */
	public void downloadEventPerformed(DownloadStatusStateEvent downloadStatusStateEvent);

	/**
	 * A download connected event was performed.
	 * @param downloadConnectedEvent The download connected event that was performed.
	 */
	public void downloadEventPerformed(DownloadConnectedEvent downloadConnectedEvent);
}
