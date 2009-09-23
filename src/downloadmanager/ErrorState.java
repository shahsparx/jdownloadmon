package downloadmanager;

/**
 * An error state indicates an error and has an error message to describe the error.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ErrorState extends StatusState {

	/** The error message of this state. */
	private String mErrorMessage;

	/**
	 * @return The error message of this state.
	 */
	public String getErrorMessage() {
		return mErrorMessage;
	}

	/**
	 * Construct an error state with a message.
	 * @param downloadObject The downloadObject associated with this state.
	 * @param errorMessage The error message of this state.
	 */
	public ErrorState(DownloadObject downloadObject, String errorMessage) {
		super(downloadObject);
		mErrorMessage = errorMessage;
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
