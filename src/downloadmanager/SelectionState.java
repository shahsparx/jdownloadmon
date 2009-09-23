package downloadmanager;

/**
 * A selection state represents how an object is selected.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class SelectionState {

	/** The download object... */
	protected DownloadObject mDownloadObject;

	/**
	 * Construct a selction state.
	 * @param downloadObject The download object...
	 */
	protected SelectionState(DownloadObject downloadObject) {
		mDownloadObject = downloadObject;
	}
}
