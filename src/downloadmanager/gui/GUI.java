package downloadmanager.gui;

import downloadmanager.DownloadEvent;
import downloadmanager.DownloadObject;
import downloadmanager.DownloadObserver;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The GUI for the Download Manager.
 * [singleton]
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class GUI implements DownloadObserver {

	/** The singleton instance of the GUI. */
	public static final GUI INSTANCE = new GUI();
	/** The download queue used in this gui. */
	private DownloadQueue mQueue;
	/** The button used for starting selected downloads. */
	private JButton mStartButton;
	/** The button used for stopping selected downloads. */
	private JButton mStopButton;
	/** The button used for removing selected downloads. */
	private JButton mRemoveButton;
	/** The button used to move selected downloads up the list. */
	private JButton mMoveUpQueueButton;
	/** The button used to move selected downloads down the list. */
	private JButton mMoveDownQueueButton;


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
	 * Private constructor for GUI.
	 */
	private GUI() {
		DownloadTableModel model = new DownloadTableModel();
		mQueue = new DownloadQueue(model);
		mStartButton = new JButton("start");
		mStopButton =  new JButton("stop");
		mRemoveButton =  new JButton("remove");
		mMoveUpQueueButton = new JButton("up");
		mMoveDownQueueButton = new JButton("down");

		setupGUI();
	}

	/**
	 * Set up the gui with a frame and buttons.
	 */
	private void setupGUI() {
		JPanel topButtonsPanel = new JPanel();
		topButtonsPanel.setLayout(new FlowLayout());
		JButton button = new JButton("new");
		new NewDownloadButton(button);
		topButtonsPanel.add(button);
		topButtonsPanel.add(mStartButton);
		topButtonsPanel.add(mStopButton);
		topButtonsPanel.add(mRemoveButton);

		JPanel bottomButtonsPanel = new JPanel();
		bottomButtonsPanel.setLayout(new FlowLayout());
		bottomButtonsPanel.add(mMoveUpQueueButton);
		bottomButtonsPanel.add(mMoveDownQueueButton);

		JPanel middlePanel = new JPanel();
		JScrollPane pane = new JScrollPane(mQueue);
		middlePanel.add(pane);

		JFrame frame = new JFrame("jDownloadMon");
		Container content = frame.getContentPane();
		
		content.add(topButtonsPanel, BorderLayout.NORTH);
		content.add(middlePanel, BorderLayout.CENTER);
		content.add(bottomButtonsPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Update the download queue.
	 * @param downloadEvent The download event with the new information.
	 */
	public void downloadEventPerformed(DownloadEvent downloadEvent) {
		mQueue.update(downloadEvent);
	}

	/**
	 * Add a download object to the queue.
	 * @param downloadObject The download object to add.
	 */
	public void addDownloadObject(DownloadObject downloadObject) {
		DownloadView downloadView = new DownloadView(downloadObject);
		mQueue.addDownload(downloadView);
	}

	/**
	 * @return The start button.
	 */
	public JButton getStartButton() {
		return mStartButton;
	}

	/**
	 * @return The stop button.
	 */
	public JButton getStopButton() {
		return mStopButton;
	}

	/**
	 * @return The remove button.
	 */
	public JButton getRemoveButton() {
		return mRemoveButton;
	}
	/**
	 * @return The move up queue button.
	 */
	public JButton getMoveUpQueueButton() {
		return mMoveUpQueueButton;
	}

	/**
	 * @return The move down queue button.
	 */
	public JButton getMoveDownQueueButton() {
		return mMoveDownQueueButton;
	}
}
