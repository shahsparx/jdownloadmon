package jdownloadmon.gui;

import jdownloadmon.gui.renderers.DownloadRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * A Table model for ZE DOWNLOADZ
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadTableModel extends DefaultTableModel {

	/** A vector for the rows to display on a table. */
	private Vector<Vector<DownloadRenderer>> mRows;
	/** Hashmap of the views and vectors linked to them. */
	private HashMap<Vector<DownloadRenderer>, DownloadView> mViews;
	/** The sorting column which sorting is applied on. */
	protected int mSortColumn = -1;
	/** Boolean representing whether sorting is done ascending or descending. */
	protected boolean mIsSortAsc = true;

	/**
	 * The columns in this table.
	 */
	public enum Columns {

		/** Queue position column. */
		Position,
		/** Filename column. */
		Filename,
		/** Size column. */
		Size,
		/** ETA column. */
		ETA,
		/** Speed column */
		Speed,
		/** Status state icon column. */
		Status,
		/** Progress bar column. */
		Progress;
	}

	/**
	 * Construct a download table model.
	 */
	public DownloadTableModel() {
		mViews = new HashMap<Vector<DownloadRenderer>, DownloadView>();
		setColumnIdentifiers(Columns.values());
		mRows = new Vector<Vector<DownloadRenderer>>();
		this.setDataVector(mRows, columnIdentifiers);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		String columnName = "";
		
		if (column == 0) {
				columnName += "#";
		} else {
			columnName += Columns.values()[column].toString();
		}

		if (column == mSortColumn) {
			columnName += mIsSortAsc ? " +" : " -";
		}

		return columnName;
	}

	/**
	 * Convert a download view into a table row with data and add it.
	 * @param downloadView The download view to add.
	 */
	public void addDownloadView(DownloadView downloadView) {
		Vector<DownloadRenderer> row = createRow(downloadView);
		mViews.put(row, downloadView);
		mRows.addElement(row);
		this.fireTableRowsInserted(mRows.size() - 1, mRows.size() - 1);
	}

	/**
	 * @return <tt>true</tt> if this table model is currently using a sorting method, <tt>false</tt> otherwise.
	 */
	public boolean isSorting() {
		return mSortColumn > -1 && mSortColumn < Columns.values().length;
	}

	/**
	 * Create a vector from a dnwnload view.
	 * @param downloadView Download view to extract info from.
	 */
	private Vector<DownloadRenderer> createRow(DownloadView downloadView) {
		Vector<DownloadRenderer> v = new Vector<DownloadRenderer>();
		v.addElement(downloadView.getPositionRenderer());
		v.addElement(downloadView.getFilenameRenderer());
		v.addElement(downloadView.getSizeRenderer());
		v.addElement(downloadView.getETARenderer());
		v.addElement(downloadView.getSpeedRenderer());
		v.addElement(downloadView.getViewStateRenderer());
		v.addElement(downloadView.getProgressBarRenderer());

		return v;
	}

	/**
	 * Remove a download view.
	 * @param row The row to remove.
	 */
	public void removeDownloadView(int row) {
		Vector<DownloadRenderer> vector = mRows.remove(row);
		mViews.remove(vector);
		this.fireTableRowsDeleted(row, row);
	}

	/**
	 * Sort all rows by the column specified.
	 * @param column Which column to sort by.
	 */
	public void sortRowsByColumn(int column) {
		if (column > -1 && column < Columns.values().length) {
			Collections.sort(mRows, new ColumnComparator<Vector<DownloadRenderer>>(mIsSortAsc, column));
		}
	}

	/**
	 * Sort all rows using the last used column.
	 */
	public void sortRows() {
		sortRowsByColumn(mSortColumn);
	}

	/**
	 * Get a view from a certain row.
	 * @param row The row to retrieve the download view from.
	 * @return The download view that's in that row.
	 */
	public DownloadView getViewAt(int row) {
		return mViews.get((Vector)mRows.get(row));
	}

	/**
	 * Get a row that a certain view is in.
	 * @param view The view that's in the row.
	 * @return The row, as an integer, where the view is located
	 * or <tt>-1</tt> if the view is not in any row.
	 */
	public int getRowForView(DownloadView view) {
		for (int i = 0; i < mRows.size(); i++) {
			if (getViewAt(i) == view) {
				return i;
			}
		} 

		return -1;
	}

	/**
	 * Column listener for listening for column header clicks.
	 */
	class ColumnListener extends MouseAdapter {

		/** The table that this column listener is listening to. */
		private DownloadQueue mQueue;

		/**
		 * Construct a column listener.
		 * @param table The table to listen to and perform actions on.
		 */
		public ColumnListener(DownloadQueue queue) {
			mQueue = queue;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			TableColumnModel colModel = mQueue.getColumnModel();
			int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
			int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

			if (mSortColumn == modelIndex) {
				if (!mIsSortAsc) {
					mSortColumn = -1;
				}
				
				mIsSortAsc = !mIsSortAsc;
			} else {
				mSortColumn = modelIndex;
			}

			// TODO update to use header renderer instead with sorting triangle icons.
			for (int i = 0; i < Columns.values().length; i++) {
				TableColumn col = colModel.getColumn(i);
				col.setHeaderValue(getColumnName(col.getModelIndex()));
			}

			mQueue.sort();
		}
	}
}
