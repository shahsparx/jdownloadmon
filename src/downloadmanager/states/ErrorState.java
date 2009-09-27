package downloadmanager.states;

import downloadmanager.*;

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
		mDownloadObject.setStatusState(new ActiveState(mDownloadObject));
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromErrorList(mDownloadObject);
	}

	@Override
	public boolean changeTo() {
		return DownloadManager.INSTANCE.addToErrorList(mDownloadObject);
	}

	@Override
	public void stop() {
		//do nothing, not active
	}
}
