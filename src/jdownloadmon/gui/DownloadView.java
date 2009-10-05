package jdownloadmon.gui;

import jdownloadmon.gui.viewStates.ViewState;
import jdownloadmon.DownloadObject;
import jdownloadmon.gui.renderers.ProgressBarRenderer;
import jdownloadmon.gui.renderers.TextRenderer;
import jdownloadmon.gui.renderers.ViewStateRenderer;
import jdownloadmon.states.ActiveState;
import java.util.concurrent.TimeUnit;

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
	private TextRenderer mPositionRenderer;
	/** The filename renderer of this download view. */
	private TextRenderer mFilenameRenderer;
	/** The size renderer of this download view. */
	private TextRenderer mSizeRenderer;
	/** The ETA renderer of this download view. */
	private TextRenderer mETARenderer;
	/** The speed renderer of this download view. */
	private TextRenderer mSpeedRenderer;



	/**
	 * Construct a download component.
	 * @param downloadObject The download object to be viewed.
	 * @param viewStateRenderer The renderer used to render the view state.
	 */
	public DownloadView(DownloadObject downloadObject, ViewStateRenderer viewStateRenderer) {
		mDownloadObject = downloadObject;
		mViewStateRenderer = viewStateRenderer;
		mProgressBarRenderer = new ProgressBarRenderer();
		mFilenameRenderer = new TextRenderer(getFileName());
		mPositionRenderer = new TextRenderer(getQueuePosition());
		mSizeRenderer = new TextRenderer(getSize());
		mETARenderer = new TextRenderer(getETA());
		mSpeedRenderer = new TextRenderer(getSpeed());
	}

	/**
	 * @return The Speed renderer of this download view.
	 */
	public TextRenderer getSpeedRenderer() {
		return mSpeedRenderer;
	}

	/**
	 * @return The ETA renderer of this download view.
	 */
	public TextRenderer getETARenderer() {
		return mETARenderer;
	}

	/**
	 * @return The size renderer of this download view.
	 */
	public TextRenderer getSizeRenderer() {
		return mSizeRenderer;
	}

	/**
	 * @return The speed, in kiB/s.
	 */
	public String getSpeed() {
		double speed = mDownloadObject.getSpeed();
		if (speed > 0) {
			int counter = 0;
			while (speed > 10000) {
				speed /= 1000;
				counter++;
			}
			
			String unit;
			if (counter == 0) {
				unit = "B/s";
			} else if (counter == 1) {
				unit = "kB/s";
			} else if (counter == 2) {
				unit = "MB/s";
			} else if (counter == 3) {
				unit = "GB/s";
			} else if (counter == 4) {
				unit = "TB/s";
			} else {
				unit = "w00t/s";
			}

			return String.format("%02.1f", speed) + " " + unit;
		} else {
			return "";
		}
	}

	/**
	 * @return The estimated time of arrival as days:hours:minutes:seconds.
	 */
	public String getETA() {
		long eta = mDownloadObject.getETA();
		if (eta > 0) {
			eta = mDownloadObject.getETA();
			long days = eta / (1000 * 60 * 60 * 24);
			eta -= days * 1000 * 60 * 60 * 24;
			long hours = eta / (1000 * 60 * 60);
			eta -= hours * 1000 * 60 * 60;
			long minutes = eta / (1000 * 60);
			eta -= minutes * 1000 * 60;
			long seconds = eta / 1000;


			return days + "d:" + hours + "h:" + minutes + "m:" + seconds + "s";
		} else {
			return "";
		}
	}

	/**
	 * Update the ETA renderer.
	 */
	public void updateETA() {
		mETARenderer.setDisplayText(getETA());
	}

	/**
	 * Update the speed renderer.
	 */
	public void updateSpeed() {
		mSpeedRenderer.setDisplayText(getSpeed());
	}

	/**
	 * Update the size renderer.
	 */
	public void updateSize() {
		mSizeRenderer.setDisplayText(getSize());
	}

	/**
	 * @return The size, in kibibytes of this download view's download object.
	 */
	public String getSize() {
		return mDownloadObject.getSize() / 1024 + " kiB";
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
	public TextRenderer getFilenameRenderer() {
		return mFilenameRenderer;
	}

	/**
	 * Get the position renderer.
	 * @return The position renderer wrapped in this download view.
	 */
	public TextRenderer getPositionRenderer() {
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
		mPositionRenderer.setDisplayText(getQueuePosition());
	}

	/**
	 * Update this download view's filename.
	 */
	public void updateFileName() {
		mFilenameRenderer.setDisplayText(getFileName());
	}
}
