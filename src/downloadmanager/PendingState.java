package downloadmanager;

/**
 * A pending state is a "waiting to be active" state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class PendingState extends StatusState{

    /**
     * Construct a pending state.
     * @param downloadObject The downloadObject associated with this state.
     */
    public PendingState(DownloadObject downloadObject) {
        super(downloadObject);
    }

    @Override
    public void download() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
