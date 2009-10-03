package downloadmanager.gui;

import downloadmanager.gui.renderers.DownloadRenderer;
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
	 * Compare two objects, in this case cells of a JTable.
	 * @param o1 The first object to compare.
	 * @param o2 The second object to compare.
	 * @return A negative integer, 0 or a positive integer depending on whehter o1 is less, equal to or greater than o2.
	 */
	public int compare(T o1, T o2) {
		DownloadRenderer firstRenderer = o1.get(mColumnIndex);
		DownloadRenderer secondRenderer = o2.get(mColumnIndex);

		String first = firstRenderer.toString();
		String second = secondRenderer.toString();

		int result = 0;
		// put empty strings last.
		if (first.equals("")) {
			result = 1;
		} else if (second.equals("")) {
			result = -1;
		} else {
			result = first.compareTo(second);
			if (!mIsSortAsc) {
				result = -result;
			}
		}
		
		return result;
	}
}
