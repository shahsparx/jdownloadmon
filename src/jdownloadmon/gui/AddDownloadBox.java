package jdownloadmon.gui;

import jdownloadmon.DownloadManager;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

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
		mURLTextField.setText("http://googleisagiantrobot.com/google-is-a-giant-robot.png");
		mURLTextField.setToolTipText("URL Textfield");
		JPopupMenu popup = new JPopupMenu();
		JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
		item.setText("Cut");
		popup.add(item);
		item = new JMenuItem(new DefaultEditorKit.CopyAction());
		item.setText("Copy");
		popup.add(item);
		item = new JMenuItem(new DefaultEditorKit.PasteAction());
		item.setText("Paste");
		popup.add(item);
		mURLTextField.setComponentPopupMenu(popup);
		mDirectoryTextField.setComponentPopupMenu(popup);
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
