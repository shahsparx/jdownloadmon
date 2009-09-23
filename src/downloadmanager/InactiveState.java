package downloadmanager;

/**
 * An inactive state is a paused or stopped state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class InactiveState extends StatusState {

	/**
	 * Construct an inactive state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	public InactiveState(DownloadObject downloadObject) {
		super(downloadObject);
	}

	@Override
	public void download() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public StatusState getShallowCopy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setStatusState(StatusState state, DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
