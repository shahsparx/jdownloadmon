package jdownloadmon.states;

import jdownloadmon.*;

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
		// do nothing, already completed.
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromCompletedList(mDownloadObject);
		mDownloadObject = null;
	}

	@Override
	public boolean changeTo() {
		if (DownloadManager.INSTANCE.addToCompletedList(mDownloadObject)) {
		mDownloadObject.setStatusState(this);
		return true;
		}

		return false;
	}

	@Override
	public void pause() {
		//do nothing
	}

	@Override
	public int getQueuePosition() {
		return Integer.MAX_VALUE;
	}

	@Override
	public StatusState getShallowCopy() {
		return new CompletedState(null);
	}
}
