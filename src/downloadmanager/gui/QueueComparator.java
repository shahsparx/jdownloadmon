/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package downloadmanager.gui;

import java.util.Comparator;

/**
 * Download View Comparator to use with sorting.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class QueueComparator<T> implements Comparator<T> {

	/**
	 * Compare two Download Views.
	 * @param o1 The first download view to compare.
	 * @param o2 The second download view to compare.
	 * @return A negative integer, 0 or a positive integer depending on whehter o1 is less, equal to or greater than o2.
	 */
	public int compare(T o1, T o2) {
		String first = ((DownloadView)o1).getQueuePosition();
		String second = ((DownloadView)o2).getQueuePosition();
		//Empty strings will end up first.
		return first.compareTo(second);
	}

}
