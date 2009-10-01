/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager.gui;

import downloadmanager.gui.renderers.DownloadRenderer;
import java.util.Comparator;
import java.util.Vector;

/**
 * A column comparator used for sorting rows on different columns.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ColumnComparator<T> implements Comparator<T> {

	/** Whether or not to sort in ascending mode. */
	protected boolean mIsSortAsc;
	/** Which column index to sort on. */
	protected int mColumnIndex;

	/**
	 * Construct a column comparator.
	 * @param sortAsc If this comparator uses ascending sort method.
	 */
	public ColumnComparator(boolean sortAsc, int column) {
		mIsSortAsc = sortAsc;
		mColumnIndex = column;
	}

	/**
	 * Compare to objects, in this case cells of a JTable.
	 * @param o1 The first object to compare.
	 * @param o2 The second object to compare.
	 * @return A negative integer, 0 or a positive integer depending on whehter o1 is less, equal to or greater than o2.
	 */
	public int compare(Object o1, Object o2) {
		Vector<DownloadRenderer> firstVector = (Vector<DownloadRenderer>) o1;
		Vector<DownloadRenderer> secondVector = (Vector<DownloadRenderer>) o2;

		DownloadRenderer firstRenderer = firstVector.get(mColumnIndex);
		DownloadRenderer secondRenderer = secondVector.get(mColumnIndex);

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
