package jdownloadmon.states;

import jdownloadmon.*;

/**
 * A pending state is a "waiting to be active" state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class PendingState extends StatusState {

	/**
	 * Construct a pending state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	public PendingState(DownloadObject downloadObject) {
		super(downloadObject);
	}

	@Override
	public void download() {
		//do nothing, in pending mode already.
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromPendingList(mDownloadObject);
		mDownloadObject = null;
	}

	@Override
	public boolean changeTo() {
		return DownloadManager.INSTANCE.addToPendingList(mDownloadObject);
	}

	@Override
	public void pause() {
		mDownloadObject.changeStatusState(new InactiveState(mDownloadObject));
	}

	@Override
	public String getQueuePosition() {
		return DownloadManager.INSTANCE.getPendingQueuePosition(mDownloadObject);
	}

	@Override
	public StatusState getShallowCopy() {
		return new PendingState(null);
	}
}
