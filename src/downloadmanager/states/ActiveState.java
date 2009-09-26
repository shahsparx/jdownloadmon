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
	public void download() {
		// do nothing, already active
	}

	@Override
	public void changeFrom() {
		DownloadManager.INSTANCE.removeFromActiveList(mDownloadObject);
	}

	@Override
	public boolean changeTo() {
		return DownloadManager.INSTANCE.addToActiveList(mDownloadObject);
	}
}