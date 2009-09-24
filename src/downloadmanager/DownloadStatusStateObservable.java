package downloadmanager;

/**
 * An interface for observable download status states.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadStatusStateObservable {

	/**
	 * Add an observer to observe this observable.
	 * @param observer The observer to be notified of this observable's events.
	 */
	public void addStatusStateListener(DownloadStatusStateObserver observer);

	/**
	 * Remove an observer from this observable.
	 * @param observer The observer to stop being notified of this observable's events.
	 */
	public void removeStatusStateListener(DownloadStatusStateObserver observer);

	/**
	 * Notify all the listeners of an event.
	 * @param downloadStatusStateEvent The download event that was raised.
	 */
	public void notifyStatusStateListeners(DownloadStatusStateEvent downloadStatusStateEvent);

}
