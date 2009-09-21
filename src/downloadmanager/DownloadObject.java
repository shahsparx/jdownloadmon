package downloadmanager;

import java.util.ArrayList;

/**
 * A download object represents anything that is being downloaded.
 * The class contains methods for connecting to and downloading files among other things.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadObject implements Runnable, DownloadObservable {

    /** List of observers observing this download object. */
    private ArrayList<DownloadObserver> mDownloadObservers;
    /** The download file's destionation. */
    private String mDestination;
    /** The current downloaded size, in bytes. */
    private long mDownloadedSize;
    /** The size of the complete file. */
    private long mSize;
    /** The current state of this download object. */
    private StatusState mStatusState;
    /** The connection to the server. */
    private DownloadConnection mDownloadConnection;

    /**
     * Construct a download object.
     * @param destination Where to put the file on the drive.
     * @param connection The download connection to use for downloading.
     */
    public DownloadObject(String destination, DownloadConnection connection) {

    }

    /**
     * @see Runnable#run() 
     */
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see DownloadObservable#addListener(DownloadObserver observer)
     */
    public void addListener(DownloadObserver observer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see DownloadObservable#removeListener(DownloadObserver observer)
     */
    public void removeListener(DownloadObserver observer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see DownloadObservable#notifyListeners(DownloadEvent downloadEvent)
     */
    public void notifyListeners(DownloadEvent downloadEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get the current state of the download object.
     * @return the {@link StatusState} that this object is currently in.
     */
    public StatusState getStatusState() {
        return mStatusState;
    }

    /**
     * Get the current download percentage.
     * @return an <tt>integer</tt> value representing how far this download has gotten percentually.
     */
    public int getPercentDownloaded() {
        return 0;
    }

    /**
     * Change the state of the download object. 
     * @param state The StatusState to change to.
     */
    public void setState(StatusState state) {

    }

    /**
     * Get the destination.
     * @return Destination file path, as a String.
     */
    public String getDestination() {
        return mDestination;
    }

    /**
     * Set the destination.
     * Will have to stop downloading first for it to work.
     * @param newDestination The destination file path, as a String.
     */
    public void setDestination(String newDestination) throws AlreadyDownloadingException {
        if (!(mStatusState instanceof ActiveState)) {
        mDestination = newDestination;
        } else {
          throw new AlreadyDownloadingException("Cannot change destination, already downloading!");
        }
    }

    /**
     * Get the download connection of this download object.
     * @return The download connection of this download object.
     */
    public DownloadConnection getConnection() {
        return mDownloadConnection;
    }

    /**
     * Set the connection to a new connection.
     * Will have to stop downloading first for it to work.
     * @param downloadConnection The new connection to set to.
     */
    public void setConnection(DownloadConnection downloadConnection) throws AlreadyDownloadingException {
        if (!(mStatusState instanceof ActiveState)) {
             mDownloadConnection = downloadConnection;
        } else {
            throw new AlreadyDownloadingException("Cannot change connection, already downloading!");
        }
    }

    /**
     * Try to start the download of the download object.
     */
    public void download() {
        mStatusState.download();
    }
}
