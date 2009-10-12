package jdownloadmon.gui;

import java.util.logging.Level;
import jdownloadmon.events.DownloadConnectedEvent;
import jdownloadmon.gui.buttons.SettingsButton;
import jdownloadmon.gui.buttons.AboutButton;
import jdownloadmon.gui.buttons.NewDownloadButton;
import jdownloadmon.gui.viewStates.InactiveViewState;
import jdownloadmon.events.DownloadProgressEvent;
import jdownloadmon.DownloadObject;
import jdownloadmon.DownloadObserver;
import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.gui.renderers.ViewStateRenderer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jdownloadmon.DownloadLogger;
import jdownloadmon.DownloadManager;
import jdownloadmon.XMLDownloadsFile;

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
	/** All the buttons in the gui that are used to perform download operations. */
	private ArrayList<JButton> mButtons;
	/** The frame used. */
	private JFrame mFrame;

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
	 * Set the cursor to the hourglass cursor.
	 */
	public void setThinkingCursor() {
		mFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	}

	/**
	 * Set the cursor to the default cursor.
	 */
	public void setDefaultCursor() {
		mFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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

		JPanel topButtonsPanel = new JPanel(new FlowLayout());
		JButton newDownloadButton = new JButton(IconStore.INSTANCE.getImageIcon("add.png"));

		new NewDownloadButton(newDownloadButton);
		topButtonsPanel.add(newDownloadButton);
		topButtonsPanel.add(mStartButton);
		topButtonsPanel.add(mStopButton);
		topButtonsPanel.add(mRemoveButton);


		JButton settingsButton = new JButton(IconStore.INSTANCE.getImageIcon("config.png"));
		new SettingsButton(settingsButton);
		JButton aboutButton = new JButton(IconStore.INSTANCE.getImageIcon("about.png"));
		new AboutButton(aboutButton);
		JPanel bottomButtonsPanel = new JPanel(new FlowLayout());
		bottomButtonsPanel.add(settingsButton);
		bottomButtonsPanel.add(mMoveUpQueueButton);
		bottomButtonsPanel.add(mMoveDownQueueButton);
		bottomButtonsPanel.add(aboutButton);


		JPanel middlePanel = new JPanel();
		JScrollPane pane = new JScrollPane(mQueue);
		pane.setPreferredSize(new Dimension(600, 400));
		middlePanel.add(pane);

		mFrame = new DownloadFrame("jDownloadMon");
		Container content = mFrame.getContentPane();

		content.add(topButtonsPanel, BorderLayout.NORTH);
		content.add(middlePanel, BorderLayout.CENTER);
		content.add(bottomButtonsPanel, BorderLayout.SOUTH);

		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setResizable(false);
		mFrame.pack();
		mFrame.setVisible(true);

		setThinkingCursor();
		try {
			XMLDownloadsFile downloadsFile = XMLDownloadsFile.loadFile();
			if (downloadsFile == null) {
				downloadsFile = new XMLDownloadsFile(new ArrayList<DownloadObject>());
				downloadsFile.saveFile();
			} else {
				for (DownloadObject object : downloadsFile.getDownloads()) {
					DownloadManager.INSTANCE.addDownload(object);
					addDownloadObject(object);
					// make sure the listeners know how many percent are downloaded.
					object.notifyListeners(new DownloadProgressEvent(object));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DownloadLogger.LOGGER.log(Level.SEVERE, e.toString());
		}

		setDefaultCursor();
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

	public void downloadEventPerformed(DownloadConnectedEvent downloadConnectedEvent) {
		mQueue.update(downloadConnectedEvent);
	}
}
