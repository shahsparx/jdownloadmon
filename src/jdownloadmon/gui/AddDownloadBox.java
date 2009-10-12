package jdownloadmon.gui;

import jdownloadmon.DownloadManager;
import javax.swing.JTextField;
import jdownloadmon.Constants;

/**
 * The add download dialog box is used for adding a new download to the download manager.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AddDownloadBox {

	/** The URL textfield in this box. */
	private JTextField mURLTextField;
	/** The Directory textfield in this box. */
	private JTextField mDirectoryTextField;

	/**
	 * Construct an add download dialog box.
	 * @param urlField The URL textfield that this download box uses.
	 * @param directoryTextField The directory textbox this download box uses.
	 */
	public AddDownloadBox(JTextField urlField, JTextField directoryTextField) {
		mURLTextField = urlField;
		mDirectoryTextField = directoryTextField;
		mDirectoryTextField.setText(DownloadManager.INSTANCE.getDefaultDirectory());
		mDirectoryTextField.setToolTipText("Directory Textfield");
		mURLTextField.setToolTipText("URL Textfield");
		if (!Constants.RELEASE) {
			mURLTextField.setText("http://googleisagiantrobot.com/google-is-a-giant-robot.png");
		}
	}

	/**
	 * Get the URL textfield of this add download dialog box.
	 * @return The URL textfield of this add download box.
	 */
	public JTextField getURLTextField() {
		return mURLTextField;
	}

	/**
	 * Get the directory textfield of this box.
	 * @return The directory textfield.
	 */
	public JTextField getDirectoryTextField() {
		return mDirectoryTextField;
	}
}
