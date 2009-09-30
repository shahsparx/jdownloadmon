package downloadmanager.gui;

import downloadmanager.gui.renderers.PositionRenderer;
import downloadmanager.gui.viewStates.ViewState;
import downloadmanager.DownloadObject;
import downloadmanager.gui.renderers.FilenameRenderer;
import downloadmanager.gui.renderers.ProgressBarRenderer;
import downloadmanager.gui.renderers.ViewStateRenderer;

/**
 * A download component is a viewable download object.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadView {

	/** The download object wrapped in this download view. */
	private DownloadObject mDownloadObject;
	/** The view state renderer of this download view. */
	private ViewStateRenderer mViewStateRenderer;
	/** The progress bar renderer of this download view. */
	private ProgressBarRenderer mProgressBarRenderer;
	/** The position renderer of this download view. */
	private PositionRenderer mPositionRenderer;
	/** The filename renderer of this download view. */
	private FilenameRenderer mFilenameRenderer;

	/**
	 * Construct a download component.
	 * @param downloadObject The download object to be viewed.
	 * @param viewStateRenderer The renderer used to render the view state.
	 */
	public DownloadView(DownloadObject downloadObject, ViewStateRenderer viewStateRenderer) {
		mDownloadObject = downloadObject;
		mViewStateRenderer = viewStateRenderer;
		mProgressBarRenderer = new ProgressBarRenderer();
		mFilenameRenderer = new FilenameRenderer(getFileName());
		mPositionRenderer = new PositionRenderer(getQueuePosition());
	}

	/**
	 * Set the value of the progress bar.
	 * @param percentage The percentage to set it to.
	 */
	public void setProgressBarValue(int percentage) {
		mProgressBarRenderer.setProgressBarValue(percentage);
	}

	/**
	 * Get the progress bar renderer
	 * @return The progress bar renderer wrapped in this download view.
	 */
	public ProgressBarRenderer getProgressBarRenderer() {
		return mProgressBarRenderer;
	}

	/**
	 * Get the filename renderer.
	 * @return The filename renderer wrapped in this download view.
	 */
	public FilenameRenderer getFilenameRenderer() {
		return mFilenameRenderer;
	}

	/**
	 * Get the position renderer.
	 * @return The position renderer wrapped in this download view.
	 */
	public PositionRenderer getPositionRenderer() {
		return mPositionRenderer;
	}

	/**
	 * Get the file name of the download object.
	 * @return The file name and extension of the download object as a <tt>String</tt>.
	 */
	public String getFileName() {
		String[] path = mDownloadObject.getDestination().split("/");
		return path[path.length - 1];
	}

	/**
	 * Get the status of the download (one of active, inactive, pending, error, completed).
	 * @return status of the download.
	 */
	public ViewStateRenderer getViewStateRenderer() {
		return mViewStateRenderer;
	}

	/**
	 * Change the view state to another view state.
	 * @param viewState The view state to change to.
	 */
	public void setViewState(ViewState viewState) {
		mViewStateRenderer.setViewState(viewState);
	}

	/**
	 * Get the download object wrapped in this view.
	 * @return The download object wrapped in this view.
	 */
	public DownloadObject getDownloadObject() {
		return mDownloadObject;
	}

	/**
	 * @return The queue position of this download or an empty string if the download is not active or pending.
	 */
	public String getQueuePosition() {
		return mDownloadObject.getQueuePosition();
	}

	/**
	 * Remove the download object.
	 */
	public void remove() {
		mDownloadObject = null;
	}

	/**
	 * Update this download view's queue position.
	 */
	public void updateQueuePosition() {
		mPositionRenderer.setQueuePosition(getQueuePosition());
	}

	/**
	 * Update this download view's filename.
	 */
	public void updateFileName() {
		mFilenameRenderer.setFilename(getFileName());
	}
}
