package jdownloadmon.states;

import jdownloadmon.*;

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
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromActiveList(mDownloadObject);
		mDownloadObject = null;
	}

	@Override
	public boolean changeTo() {
		if (DownloadManager.INSTANCE.addToActiveList(mDownloadObject)) {
			// Set the status state before starting new thread because the while loop in the run method
			// depends on the status state being active.
			mDownloadObject.setStatusState(this);
			Thread t = new Thread(mDownloadObject);
			t.start();
			return true;
		}

		mDownloadObject.changeStatusState(new PendingState(mDownloadObject));
		return false;
	}

	@Override
	public void download() {
		//do nothing, already active
	}

	@Override
	public void pause() {
		mDownloadObject.changeStatusState(new InactiveState(mDownloadObject));
	}

	@Override
	public int getQueuePosition() {
		return DownloadManager.INSTANCE.getActiveQueuePosition(mDownloadObject);
	}

	@Override
	public StatusState getShallowCopy() {
		return new ActiveState(null);
	}

	@Override
	public void openFile() {
		// do nothing, can't open a file that's being written to.
	}
}
