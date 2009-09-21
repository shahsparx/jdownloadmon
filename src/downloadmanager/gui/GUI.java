package downloadmanager.gui;

import downloadmanager.DownloadEvent;

/**
 * The GUI for the Download Manager.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class GUI {

    /** The download queue used in this gui. */
    private DownloadQueue mQueue;

    /**
     * Sort type enum for sorting downloads in the queue.
     * Backward represents descending, otherwise it's ascending order.
     */
    public enum SortType {
        /** Sort by queue position (i.e the objects' own order). */
        POSITION(1),
        /** Sort by filename. */
        FILENAME(2),
        /** Sort by downloaded size. */
        DOWNLOADED(4),
        /** Sort by estimated time of arrival */
        ETA(8),
        /** Sort by percent done. */
        PERCENT(16),
        /** Take any sort method and do it in backward (descending) order. */
        BACKWARD(32);

        /** This enum's flag. */
        private int mFlag;

        /**
         * Construct an enum.
         * @param flag The flag this enum uses.
         */
        SortType(int flag) {
            mFlag = flag;
        }

        /**
         * Get the enum's flag.
         * @return The flag of the enum.
         */
        public int getFlag() {
            return mFlag;
        }
    }

    /**
     * Construct a GUI.
     * @param queue The queue this GUI uses.
     */
    public GUI(DownloadQueue queue) {
        mQueue = queue;
    }

    /**
     * Update the download queue.
     * @param downloadEvent The download event with the new information.
     */
    public void updateQueue(DownloadEvent downloadEvent) {

    }
}
