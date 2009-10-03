package downloadmanager.gui;

import downloadmanager.gui.viewStates.InactiveViewState;
import downloadmanager.events.DownloadProgressEvent;
import downloadmanager.DownloadObject;
import downloadmanager.DownloadObserver;
import downloadmanager.events.DownloadStatusStateEvent;
import downloadmanager.gui.renderers.ViewStateRenderer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	/** All the buttons. */
	private ArrayList<JButton> mButtons;

	/**
	 * Private constructor for GUI.
	 */
	private GUI() {
		DownloadTableModel model = new DownloadTableModel();
		mQueue = new DownloadQueue(model);
		mButtons = new ArrayList<JButton>();

		mButtons.add(mStartButton = new JButton(IconStore.INSTANCE.getImageIcon("start.png")));
		mButtons.add(mStopButton = new JButton(IconStore.INSTANCE.getImageIcon("stop.png")));
		mButtons.add(mRemoveButton = new JButton(IconStore.INSTANCE.getImageIcon("remove.png")));
		mButtons.add(mMoveUpQueueButton = new JButton(IconStore.INSTANCE.getImageIcon("up.png")));
		mButtons.add(mMoveDownQueueButton = new JButton(IconStore.INSTANCE.getImageIcon("down.png")));
	}


	/**
	 * Set up the gui with a frame and buttons.
	 */
	public void init() {
		for (JButton b : mButtons) {
			String[] split = b.toString().split("/");
			split = split[split.length - 1].split(",");
			split = split[0].split(".png");
			String name = split[0];
			b.setToolTipText(name);
			b.setFocusPainted(false);
			b.addActionListener(mQueue);
		}

		JPanel topButtonsPanel = new JPanel();
		topButtonsPanel.setLayout(new FlowLayout());
		JButton button = new JButton(IconStore.INSTANCE.getImageIcon("add.png"));
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

		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Options");

		bar.add(menu);
		frame.setJMenuBar(bar);

		frame.pack();
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Add a download object to the queue.
	 * @param downloadObject The download object to add.
	 */
	public void addDownloadObject(DownloadObject downloadObject) {
		downloadObject.addListener(this);
		DownloadView downloadView = new DownloadView(downloadObject,
				new ViewStateRenderer(new InactiveViewState()));
		mQueue.addDownloadView(downloadView);
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

	public void downloadEventPerformed(DownloadProgressEvent downloadProgressEvent) {
		mQueue.update(downloadProgressEvent);
	}

	public void downloadEventPerformed(DownloadStatusStateEvent downloadStatusStateEvent) {
		mQueue.update(downloadStatusStateEvent);
	}
}
