package downloadmanager.states;

import downloadmanager.*;

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
			Thread t = new Thread(mDownloadObject);
			t.start();
			return true;
		}

		mDownloadObject.setStatusState(new PendingState(mDownloadObject));
		return false;
	}

	@Override
	public void download() {
		//do nothing, already active
	}

	@Override
	public void stop() {
		mDownloadObject.setStatusState(new InactiveState(mDownloadObject));
	}
}
