package downloadmanager;

/**
 * Download progress observer interface for observers of download observables.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadProgressObserver {

	/**
	 * A download progress event was performed.
	 * @param downloadProgressEvent The download progress event that was performed.
	 */
	public void downloadProgressEventPerformed(DownloadProgressEvent downloadProgressEvent);
}
