package jdownloadmon.states;

import jdownloadmon.*;

/**
 * An inactive state is a paused or stopped state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class InactiveState extends StatusState {

	/**
	 * Construct an inactive state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	public InactiveState(DownloadObject downloadObject) {
		super(downloadObject);
	}

	@Override
	public void download() {
		mDownloadObject.changeStatusState(new ActiveState(mDownloadObject));
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromInactiveList(mDownloadObject);
		mDownloadObject = null;
	}

	@Override
	public boolean changeTo() {
		if (DownloadManager.INSTANCE.addToInactiveList(mDownloadObject)) {
		mDownloadObject.setStatusState(this);
		return true;
		}

		return false;
	}

	@Override
	public void pause() {
		//do nothing, already inactive
	}

	@Override
	public int getQueuePosition() {
		return Integer.MAX_VALUE;
	}

	@Override
	public StatusState getShallowCopy() {
		return new InactiveState(null);
	}
}
