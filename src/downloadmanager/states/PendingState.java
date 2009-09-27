package downloadmanager.states;

import downloadmanager.*;

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
	}

	@Override
	public boolean changeTo() {
		return DownloadManager.INSTANCE.addToPendingList(mDownloadObject);
	}

	@Override
	public void stop() {
		mDownloadObject.setStatusState(new InactiveState(mDownloadObject));
	}
}
