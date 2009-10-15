package jdownloadmon.gui.buttons;

import jdownloadmon.gui.IconStore;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import jdownloadmon.DownloadManager;
import jdownloadmon.DownloadManager.DefaultFileExistsBehavior;
import jdownloadmon.XMLConfigFile;

/**
 * A class for wrapping the behavior of the options menu item.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class SettingsButton extends FrameButton {

	/** The text field for setting directory. */
	private JTextField mDirectoryField;
	/** The spinner with integer values for setting max downloads. */
	private JSpinner mMaxDownloadsSpinner;
	/** The combo box for setting default file exists behavior. */
	private JComboBox mFileExistsBox;
	/** The config file. */
	private XMLConfigFile mConfigFile;

	/**
	 * Construct an options button.
	 * @param button The wrapped button.
	 */
	public SettingsButton(JButton button) {
		super(button, "Options");

		button.setToolTipText("settings");

		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel allPanel = new JPanel();
		allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

		Dimension size = new Dimension(120, 25);

		JLabel directoryLabel = new JLabel("Default Directory:");
		directoryLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mDirectoryField = new JTextField(15);
		setPopupMenu(mDirectoryField);
		JButton browseButton = new JButton("Browse..");
		new DirectoryChooserButton(browseButton, mDirectoryField);
		browseButton.setMargin(new Insets(4, 4, 4, 4));
		browseButton.setMaximumSize(size);
		browseButton.setPreferredSize(size);


		JLabel spinnerLabel = new JLabel("Maximum Active Downloads (0 = infinite):", JLabel.CENTER);
		spinnerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1);
		mMaxDownloadsSpinner = new JSpinner(spinnerModel);

		mMaxDownloadsSpinner.setMaximumSize(size);
		mMaxDownloadsSpinner.setPreferredSize(size);
		JFormattedTextField tf = ((JSpinner.DefaultEditor) mMaxDownloadsSpinner.getEditor()).getTextField();
		tf.setEditable(false);

		JLabel fileExistsLabel = new JLabel("Default File Exists Behavior:");
		fileExistsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mFileExistsBox = new JComboBox(DefaultFileExistsBehavior.values());
		mFileExistsBox.setMaximumSize(size);
		mFileExistsBox.setPreferredSize(size);

		JButton saveButton = new JButton(IconStore.INSTANCE.getImageIcon("save.png"));
		saveButton.setToolTipText("save");

		new Button(saveButton) {

			@Override
			public void push() {
				String directory = mDirectoryField.getText();
				int maxDownloads = (Integer) mMaxDownloadsSpinner.getValue();
				DefaultFileExistsBehavior behavior = DefaultFileExistsBehavior.valueOf(
						mFileExistsBox.getSelectedItem().toString());
				mConfigFile = new XMLConfigFile(directory, maxDownloads, behavior);
				DownloadManager.INSTANCE.saveSettings(mConfigFile);
				mFrame.setVisible(false);
			}
		};

		topPanel.add(directoryLabel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(mDirectoryField);
		topPanel.add(browseButton);
		middlePanel.add(spinnerLabel);
		middlePanel.add(Box.createHorizontalGlue());
		middlePanel.add(mMaxDownloadsSpinner);
		bottomPanel.add(fileExistsLabel);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(mFileExistsBox);
		allPanel.add(topPanel);
		allPanel.add(middlePanel);
		allPanel.add(bottomPanel);
		Border empty = new EmptyBorder(5, 5, 5, 5);
		allPanel.setBorder(empty);
		topPanel.setBorder(empty);
		bottomPanel.setBorder(empty);
		middlePanel.setBorder(empty);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(saveButton);

		Container container = mFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(allPanel);
		container.add(buttonPanel);
		mFrame.pack();
	}

	@Override
	public void push() {
		mConfigFile = DownloadManager.INSTANCE.getSettings();
		mDirectoryField.setText(mConfigFile.getDefaultDirectory());
		mMaxDownloadsSpinner.setValue(mConfigFile.getMaxDownloads());
		mFileExistsBox.setSelectedItem(mConfigFile.getDefaultFileExistsBehavior());
		super.push();
	}
}
