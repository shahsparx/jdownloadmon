package jdownloadmon.gui;

import java.io.File;
import jdownloadmon.gui.viewStates.ViewState;
import jdownloadmon.DownloadObject;
import jdownloadmon.gui.renderers.ProgressBarRenderer;
import jdownloadmon.gui.renderers.TextRenderer;
import jdownloadmon.gui.renderers.ValueRenderer;
import jdownloadmon.gui.renderers.ViewStateRenderer;

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
	private ValueRenderer mPositionRenderer;
	/** The filename renderer of this download view. */
	private TextRenderer mFilenameRenderer;
	/** The size renderer of this download view. */
	private ValueRenderer mSizeRenderer;
	/** The ETA renderer of this download view. */
	private ValueRenderer mETARenderer;
	/** The speed renderer of this download view. */
	private ValueRenderer mSpeedRenderer;



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
		mPositionRenderer = new ValueRenderer("", getQueuePosition());
		mSizeRenderer = new ValueRenderer(getSize(), downloadObject.getSize());
		mETARenderer = new ValueRenderer(getETA(), downloadObject.getETA());
		mSpeedRenderer = new ValueRenderer(getSpeed(), (long)downloadObject.getSpeed());
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
		return getValueString(mDownloadObject.getSpeed(), "B/s", "%.1f");
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
		}

		return "";
	}

	/**
	 * Update the ETA renderer.
	 */
	public void updateETA() {
		mETARenderer.setDisplayText(getETA());
		mETARenderer.setValue(mDownloadObject.getETA());
	}

	/**
	 * Update the speed renderer.
	 */
	public void updateSpeed() {
		mSpeedRenderer.setDisplayText(getSpeed());
		mSpeedRenderer.setValue((long)mDownloadObject.getSpeed());
	}

	/**
	 * Update the size renderer.
	 */
	public void updateSize() {
		mSizeRenderer.setDisplayText(getSize());
		mSizeRenderer.setValue(mDownloadObject.getSize());
	}

	/**
	 * @return The size, in kibibytes of this download view's download object.
	 */
	public String getSize() {
		return getValueString(mDownloadObject.getSize(), "B", "%.2f");
	}

	/**
	 * Get a String representation of a certain value. Used for converting to kilo, mega etc.
	 * @param value The value that is to be represented as a string.
	 * @param unit The unit of the value.
	 * @param format The format to use, for example "%.2f" for two decimals.
	 * @return
	 */
	private String getValueString(double value, String unit, String format) {
		String[] multipliers = {"","k","M","G","T","P","E","Z","Y","X","W","V","U"};
		if (value > 0) {
			int counter = 0;
			while (value > 1000) {
				value /= 1000;
				counter++;
			}

			String multiplier;

			if (counter >= multipliers.length) {
				multiplier = "ultra";
			}

			multiplier = multipliers[counter];

			return String.format(format, value) + " " + multiplier + unit;
		}

		return "";
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
		String destination = mDownloadObject.getDestination();
		return destination.substring(destination.lastIndexOf(File.separatorChar) + 1);
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
	public int getQueuePosition() {
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
		int position = getQueuePosition();
		mPositionRenderer.setValue(position);
		if (position != Integer.MAX_VALUE) {
			mPositionRenderer.setDisplayText(Integer.toString(position));
		} else {
			mPositionRenderer.setDisplayText("");
		}
	}

	/**
	 * Update this download view's filename.
	 */
	public void updateFileName() {
		mFilenameRenderer.setDisplayText(getFileName());
	}
}
