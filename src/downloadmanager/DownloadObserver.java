package downloadmanager;

/**
 * Download observer interface for observers of download observables.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public interface DownloadObserver {

    /**
     * A download event was performed.
     * @param downloadEvent The download event that was performed.
     */
    public void downloadEventPerformed(DownloadEvent downloadEvent);
}
