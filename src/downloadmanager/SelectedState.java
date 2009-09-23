package downloadmanager;

/**
 * The selected state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class SelectedState extends SelectionState {

	/**
	 * Construct a selected state.
	 * @param downloadObject The download object...
	 */
	public SelectedState(DownloadObject downloadObject) {
		super(downloadObject);
	}
}
