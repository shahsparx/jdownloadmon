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
	public StatusState getShallowCopy() {
		return new ActiveState(null);
	}

	@Override
	public void setStatusState(StatusState state, DownloadObject downloadObject) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
