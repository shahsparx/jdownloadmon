package jdownloadmon.gui;

import java.util.Comparator;

/**
 * Download View Comparator to use with sorting.
 * @param <T> The type (typically DownloadView).
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class QueueComparator<T extends DownloadView> implements Comparator<T> {

	/**
	 * Compare two Download Views.
	 * @param firstView The first download view to compare.
	 * @param secondView The second download view to compare.
	 * @return A negative integer, 0 or a positive integer depending on whehter firstView is less, equal to or greater than secondView.
	 */
	public int compare(T firstView, T secondView) {
		int first = firstView.getQueuePosition();
		int second = secondView.getQueuePosition();

		return first - second;
	}

}
