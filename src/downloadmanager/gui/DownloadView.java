package downloadmanager.gui;

import downloadmanager.DownloadObject;
import java.awt.Component;
import java.awt.Image;
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
	private ViewState mViewState;

	/**
	 * Construct a download component.
	 * @param downloadObject The download object to be viewed.
	 */
	public DownloadView (DownloadObject downloadObject, ViewState viewState) {
		mDownloadObject =  downloadObject;
		mViewState = viewState;
		mProgressBar = new JProgressBar(0, 100);
		mProgressBar.setStringPainted(true);
		mProgressBar.setValue(10);
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
		String[] strings = mDownloadObject.getDestination().split("/");
		return strings[strings.length-1];
	}

	/**
	 * @see TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int) 
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		DownloadView v = (DownloadView) value;
		return v.mProgressBar;
	}

	/**
	 * Get the status of the download (one of active, inactive, pending, error, completed).
	 * @return status of the download.
	 */
	public ViewState getViewState() {
		return mViewState;
	}

	/**
	 * Change the view state to another view state.
	 * @param viewState The view state to change to.
	 */
	public void setViewState(ViewState viewState) {
		mViewState = viewState;
	}

	/**
	 * Get the download object wrapped in this view.
	 * @return The download object wrapped in this view.
	 */
	public DownloadObject getDownloadObject() {
		return mDownloadObject;
	}
}
