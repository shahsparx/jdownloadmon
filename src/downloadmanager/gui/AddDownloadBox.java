package downloadmanager.gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

/**
 * The add download dialog box is used for adding a new download to the download manager.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AddDownloadBox {

	/** The URL textfield in this Box. */
	private JTextField mURLTextField;

	/**
	 * Construct an add download dialog box.
	 * @param field The textfield that this download box uses.
	 */
	public AddDownloadBox(JTextField field) {
		mURLTextField = field;
		mURLTextField.setText("http://googleisagiantrobot.com/google-is-a-giant-robot.png");
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
	}

	/**
	 * Get the URL textfield of this add download dialog box.
	 * @return the URL textfield of this add download box.
	 */
	public JTextField getURLTextField() {
		return mURLTextField;
	}
}
