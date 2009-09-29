package downloadmanager.states;

import downloadmanager.*;

/**
 * A StatusState is a state which a {@link DownloadObject} uses to perform certain state-based actions.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class StatusState {

	/** The download object this state is associated with. */
	protected DownloadObject mDownloadObject;

	/**
	 * Construct a status state.
	 * @param downloadObject The downloadObject associated with this state.
	 */
	protected StatusState(DownloadObject downloadObject) {
		mDownloadObject = downloadObject;
	}

	/**
	 * @return The download object wrapped in this statusState.
	 */
	public DownloadObject getDownloadObject() {
		return mDownloadObject;
	}

	/**
	 * Try to start downloading.
	 */
	public abstract void download();

	/**
	 * Try to stop downloading.
	 */
	public abstract void stop();

	/**
	 * Change a download object's state from this status state to another.
	 * @param state The status state to change to.
	 * @return <tt>true</tt> if the change was successful, <tt>false</tt> if the state could not be changed.
	 */
	public boolean changeTo(StatusState state) {
		if (mDownloadObject != null && state.changeTo()) {
			mDownloadObject.getStatusState().changeFrom();
			return true;
		}
		
		return false;
	}

	/**
	 * Change from this state.
	 */
	public abstract void changeFrom();

	/**
	 * Change to this state.
	 * @return <tt>true</tt> the change was successful, <tt>false</tt> otherwise.
	 */
	public abstract boolean changeTo();

	/**
	 * Remove a download object from the download manager.
	 */
	public void remove() {
		changeFrom();
	}
}
