package downloadmanager;

/**
 * Download status state observer interface for observers of download observables.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadStatusStateObserver {

	/**
	 * A download status state event was performed.
	 * @param downloadStatusStateEvent The download status state event that was performed.
	 */
	public void downloadStatusStateEventPerformed(DownloadStatusStateEvent downloadStatusStateEvent);

}
