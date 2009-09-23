package downloadmanager;

/**
 * A completed state indicates completion.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class CompletedState extends StatusState {

	/**
	 * Construct a completed state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	public CompletedState(DownloadObject downloadObject) {
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
