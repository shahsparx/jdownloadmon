package jdownloadmon.gui.buttons;

import jdownloadmon.gui.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

		JPanel fieldPanel = new JPanel(new GridLayout(3, 2));
		JLabel directoryLabel = new JLabel("Default Directory:");
		mDirectoryField = new JTextField(15);

		JLabel spinnerLabel = new JLabel("Max Downloads:");
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		mMaxDownloadsSpinner = new JSpinner(spinnerModel);
		JFormattedTextField tf = ((JSpinner.DefaultEditor)mMaxDownloadsSpinner.getEditor()).getTextField();
		tf.setEditable(false);

		JLabel fileExistsLabel = new JLabel("Default File Exists Behavior:");
		mFileExistsBox = new JComboBox(DefaultFileExistsBehavior.values());
		mFileExistsBox.setEditable(false);

		JButton saveButton = new JButton(IconStore.INSTANCE.getImageIcon("save.png"));
		saveButton.setToolTipText("save")
;

		new Button(saveButton) {

			@Override
			public void push() {
				String directory = mDirectoryField.getText();
				int maxDownloads = (Integer)mMaxDownloadsSpinner.getValue();
				DefaultFileExistsBehavior behavior = DefaultFileExistsBehavior.valueOf(
						mFileExistsBox.getSelectedItem().toString());
				mConfigFile = new XMLConfigFile(directory, maxDownloads, behavior);
				DownloadManager.INSTANCE.saveSettings(mConfigFile);
				mFrame.setVisible(false);
			}
		};

		fieldPanel.add(directoryLabel);
		fieldPanel.add(mDirectoryField);
		fieldPanel.add(spinnerLabel);
		fieldPanel.add(mMaxDownloadsSpinner);
		fieldPanel.add(fileExistsLabel);
		fieldPanel.add(mFileExistsBox);
		fieldPanel.setBorder(new EmptyBorder(5,5,5,5));

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(saveButton);

		Container container = mFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(fieldPanel);
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
