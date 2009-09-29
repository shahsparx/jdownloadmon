package downloadmanager.gui;

import downloadmanager.gui.viewStates.ViewState;
import downloadmanager.DownloadObject;
import downloadmanager.gui.viewStates.ViewStateRenderer;
import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * A download component is a viewable download object.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadView implements TableCellRenderer {

	/** The download object wrapped in this download view. */
	private DownloadObject mDownloadObject;
	/** The progress bar for this download component. */
	private JProgressBar mProgressBar;
	/** The Viewstate of this download view. */
	private ViewStateRenderer mViewStateRenderer;

	/**
	 * Construct a download component.
	 * @param downloadObject The download object to be viewed.
	 * @param viewStateRenderer The renderer used to render the view state.
	 */
	public DownloadView (DownloadObject downloadObject, ViewStateRenderer viewStateRenderer) {
		mDownloadObject =  downloadObject;
		mViewStateRenderer = viewStateRenderer;
		mProgressBar = new JProgressBar(0, 100);
		mProgressBar.setStringPainted(true);
	}

	/**
	 * Set the value of the progress bar.
	 * @param percentage The percentage to set it to.
	 */
	public void setProgressBarValue(int percentage) {
		mProgressBar.setValue(percentage);
	}

	/**
	 * Get the file name of the download object.
	 * @return The file name and extension of the download object as a <tt>String</tt>.
	 */
	public String getFileName() {
		String[] path = mDownloadObject.getDestination().split("/");
		return path[path.length-1];
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		DownloadView v = (DownloadView) value;
		return v.mProgressBar;
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

	void remove() {
		mDownloadObject = null;
	}
}
