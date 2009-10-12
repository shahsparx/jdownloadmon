package jdownloadmon.gui;

import jdownloadmon.gui.renderers.DownloadRenderer;
import java.util.Comparator;
import java.util.Vector;

/**
 * A column comparator used for sorting rows on different columns.
 * @param <T> The type.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ColumnComparator<T extends Vector<DownloadRenderer>> implements Comparator<T> {

	/** Whether or not to sort in ascending mode. */
	protected boolean mIsSortAsc;
	/** Which column index to sort on. */
	protected int mColumnIndex;

	/**
	 * Construct a column comparator.
	 * @param sortAsc If this comparator uses ascending sort method.
	 * @param column Which column to sort on.
	 */
	public ColumnComparator(boolean sortAsc, int column) {
		mIsSortAsc = sortAsc;
		mColumnIndex = column;
	}

	/**
	 * Compare two case cell renderers inside vectors (rows) of a JTable.
	 * Compares the cell renderers on the sorting column.
	 * @param firstVector The first vector to compare.
	 * @param secondVector The second vector to compare.
	 * @return A negative integer, 0 or a positive integer depending on whehter firstVector is less, equal to or greater than secondVector.
	 */
	public int compare(T firstVector, T secondVector) {
		DownloadRenderer firstRenderer = firstVector.get(mColumnIndex);
		DownloadRenderer secondRenderer = secondVector.get(mColumnIndex);

		String first = firstRenderer.toString();
		String second = secondRenderer.toString();

		int result = 0;
		// put empty strings last.
		if (first.equals("")) {
			if (second.equals("")) {
				result = 0;
			} else {
				result = 1;
			}
		} else if (second.equals("")) {
			result = -1;
		} else {
			result = firstRenderer.compareTo(secondRenderer);
			if (!mIsSortAsc) {
				result = -result;
			}
		}

		return result;
	}
}
