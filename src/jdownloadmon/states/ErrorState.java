package jdownloadmon.states;

import jdownloadmon.*;

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
		mDownloadObject.changeStatusState(new ActiveState(mDownloadObject));
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromErrorList(mDownloadObject);
		mDownloadObject = null;
	}

	@Override
	public boolean changeTo() {
		if (DownloadManager.INSTANCE.addToErrorList(mDownloadObject)) {
		mDownloadObject.setStatusState(this);
		return true;
		}

		return false;
	}

	@Override
	public void pause() {
		//do nothing, not active
	}

	@Override
	public int getQueuePosition() {
		return Integer.MAX_VALUE;
	}

	@Override
	public StatusState getShallowCopy() {
		return new ErrorState(null, mErrorMessage);
	}
}
