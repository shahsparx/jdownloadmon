package downloadmanager.states;

import downloadmanager.*;

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
		return DownloadManager.INSTANCE.addToCompletedList(mDownloadObject);
	}

	@Override
	public void stop() {
		//do nothing
	}
}
