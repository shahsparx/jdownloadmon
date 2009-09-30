package downloadmanager.gui;

import downloadmanager.gui.renderers.DownloadRenderer;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * A Table model for ZE DOWNLOADZ
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadTableModel extends DefaultTableModel {

	/** A vector for the rows to display on a table. */
	private Vector<Vector> mRows;
	/** List of views. */
	private ArrayList<DownloadView> mViews;

	/**
	 * The columns in this table.
	 */
	public enum Columns {

		/** Queue position column. */
		Position,
		/** Filename column. */
		Filename,
		/** Status state icon column. */
		Status,
		/** Progress bar column. */
		Progress;
	}

	/**
	 * Construct a download table model.
	 */
	public DownloadTableModel() {
		mViews = new ArrayList<DownloadView>();
		setColumnIdentifiers(Columns.values());
		mRows = new Vector<Vector>();
		this.setDataVector(mRows, columnIdentifiers);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Convert a download view into a table row with data and add it.
	 * @param downloadView The download view to add.
	 */
	public void addDownloadView(DownloadView downloadView) {
		mViews.add(downloadView);
		mRows.addElement(createRow(downloadView));
		this.fireTableRowsInserted(mRows.size() - 1, mRows.size() - 1);
	}

	/**
	 * Create a vector from a dnwnload view.
	 * @param downloadView Download view to extract info from.
	 */
	private Vector createRow(DownloadView downloadView) {
		Vector<DownloadRenderer> v = new Vector<DownloadRenderer>();
		v.addElement(downloadView.getPositionRenderer());
		v.addElement(downloadView.getFilenameRenderer());
		v.addElement(downloadView.getViewStateRenderer());
		v.addElement(downloadView.getProgressBarRenderer());

		return v;
	}

	/**
	 * Remove a download view.
	 * @param row The row to remove.
	 */
	public void removeDownloadView(int row) {
		mRows.remove(row);
		this.fireTableRowsDeleted(row, row);
		mViews.remove(row);
	}

	/**
	 * Move a download view a row up.
	 * @param row Which row the download view is initially in.
	 */
	public void moveRowUp(int row) {

		Vector tempVector = mRows.get(row - 1);
		mRows.setElementAt(mRows.get(row), row - 1);
		mRows.setElementAt(tempVector, row);
		DownloadView tempView = mViews.get(row);
		mViews.remove(row);
		mViews.add(row - 1, tempView);

		this.fireTableRowsUpdated(row - 1, row);
	}

	/**
	 * Move a download view a row down.
	 * @param row Which row the download view is initially in.
	 */
	public void moveRowDown(int row) {
		Vector tempVector = mRows.get(row + 1);
		mRows.setElementAt(mRows.get(row), row + 1);
		mRows.setElementAt(tempVector, row);
		DownloadView tempView = mViews.get(row);
		mViews.remove(row);
		mViews.add(row + 1, tempView);

		this.fireTableRowsUpdated(row, row + 1);
	}

	/**
	 * Get a view from a certain row.
	 * @param row The row to retrieve the download view from.
	 * @return The download view that's in that.
	 */
	public DownloadView getViewAt(int row) {
		return mViews.get(row);
	}

	/**
	 * Get which row a certain download view is at.
	 * @param view The download view to locate.
	 * @return The row number as an int.
	 */
	public int getRow(DownloadView view) {
		return mViews.indexOf(view);
	}
}
