package jdownloadmon;

import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.events.DownloadProgressEvent;

/**
 * An interface for observable download progresses.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadObservable {

	/**
	 * Add an observer to observe this observable.
	 * @param observer The observer to be notified of this observable's events.
	 */
	public void addListener(DownloadObserver observer);

	/**
	 * Remove an observer from this observable.
	 * @param observer The observer to stop being notified of this observable's events.
	 */
	public void removeListener(DownloadObserver observer);

	/**
	 * Notify all the listeners of an event.
	 * @param downloadProgressEvent The download event that was raised.
	 */
	public void notifyListeners(DownloadProgressEvent downloadProgressEvent);

	/**
	 * Notify all the listeners of an event.
	 * @param downloadStatusStateEvent The download event that was raised.
	 */
	public void notifyListeners(DownloadStatusStateEvent downloadStatusStateEvent);
}
