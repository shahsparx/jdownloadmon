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

		// every other column deals with integers.
		boolean integerColumn = mColumnIndex % 2 == 0;

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
			// columns that deal with integers.
			// TODO shouldn't have to get the string and parse it, should be a getValue on the renderer instead that returns a long.
			// Otherwise it will sort differently for MiB and kiB for instance.
			if (integerColumn) {
				String s1 = "";
				String s2 = "";
				for (int i = 0; i < first.length(); i++) {
					char c = first.charAt(i);
					if (Character.isDigit(c)) {
						s1 += c;
					} else {
						break;
					}
				}
				for (int i = 0; i < second.length(); i++) {
					char c = second.charAt(i);
					if (Character.isDigit(c)) {
						s2 += c;
					} else {
						break;
					}
				}
				result = Integer.parseInt(s1) - Integer.parseInt(s2);
			} else {
				result = first.compareTo(second);
			}
			if (!mIsSortAsc) {
				result = -result;
			}
		}
		
		return result;
	}
}
