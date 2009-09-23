package downloadmanager;

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
	 * Method to signalize the start of the download.
	 * For some states this will not start the download.
	 */
	public abstract void download();
	/**
	 * Get a shallow copy of this status state.
	 * @return a shallow copy of this status state.
	 */
	public abstract StatusState getShallowCopy();

	/**
	 * Change the status state of a download object.
	 * @param state The status state to change to.
	 * @param downloadObject The download object to change status state.
	 */
	public abstract void setStatusState(StatusState state, DownloadObject downloadObject);
}
