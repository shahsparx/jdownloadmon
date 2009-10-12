package jdownloadmon.states;

import jdownloadmon.DownloadManager;
import jdownloadmon.DownloadObject;

/**
 * The error state represents the status of a download object when it has failed to download.
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
