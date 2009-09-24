package downloadmanager;

/**
 * An interface for observable download progresses.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadProgressObservable {

	/**
	 * Add an observer to observe this observable.
	 * @param observer The observer to be notified of this observable's events.
	 */
	public void addProgressListener(DownloadProgressObserver observer);

	/**
	 * Remove an observer from this observable.
	 * @param observer The observer to stop being notified of this observable's events.
	 */
	public void removeProgressListener(DownloadProgressObserver observer);

	/**
	 * Notify all the listeners of an event.
	 * @param downloadProgressEvent The download event that was raised.
	 */
	public void notifyProgressListeners(DownloadProgressEvent downloadProgressEvent);
}
