package downloadmanager.states;

import downloadmanager.*;

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
		mDownloadObject.setStatusState(new ActiveState(mDownloadObject));
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromInactiveList(mDownloadObject);
	}

	@Override
	public boolean changeTo() {
		return DownloadManager.INSTANCE.addToInactiveList(mDownloadObject);
	}

	@Override
	public void stop() {
		//do nothing, already inactive
	}
}
