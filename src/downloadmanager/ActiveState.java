package downloadmanager;

/**
 * An active state is a downloading state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ActiveState extends StatusState {

	/**
	 * Construct an active state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	public ActiveState(DownloadObject downloadObject) {
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
