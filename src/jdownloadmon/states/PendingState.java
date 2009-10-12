package jdownloadmon.states;

import jdownloadmon.DownloadManager;
import jdownloadmon.DownloadObject;

/**
 * The pending state represents the status of a download object waiting to download.
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
	}

	@Override
	public boolean changeTo() {
		if (DownloadManager.INSTANCE.addToPendingList(mDownloadObject)) {
			mDownloadObject.setStatusState(this);
			return true;
		}

		return false;
	}

	@Override
	public void pause() {
		mDownloadObject.changeStatusState(new InactiveState(mDownloadObject));
	}

	@Override
	public int getQueuePosition() {
		return DownloadManager.INSTANCE.getPendingQueuePosition(mDownloadObject);
	}

	@Override
	public StatusState getShallowCopy() {
		return new PendingState(null);
	}
}
