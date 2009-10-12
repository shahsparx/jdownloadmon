package jdownloadmon.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

/**
 * A button wraps a {@link JButton} and uses its {@link #push() push} method to perform actions in a {@link jdownloadmon.DownloadManager}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class Button implements ActionListener {

	/** The JButton wrapped in this button. */
	protected JButton mButton;
	/** A popupMenu that can be used for text fields to copy, cut and paste. */
	private JPopupMenu mPopup;

	/**
	 * Construct a button.
	 * @param button The JButton used in this button.
	 */
	protected Button(JButton button) {
		mButton = button;
		mButton.setFocusPainted(false);
		mButton.addActionListener(this);

		mPopup = new JPopupMenu();
		JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
		item.setText("Cut");
		mPopup.add(item);
		item = new JMenuItem(new DefaultEditorKit.CopyAction());
		item.setText("Copy");
		mPopup.add(item);
		item = new JMenuItem(new DefaultEditorKit.PasteAction());
		item.setText("Paste");
		mPopup.add(item);
	}

	/**
	 * Set up a popup menu for a text field.
	 * @param field The field that is to have the popup menu.
	 */
	public void setPopupMenu(JTextField field) {
		field.setComponentPopupMenu(mPopup);
	}

	/**
	 * Performs a specific action in the download manager, depending on what button this is.
	 */
	public abstract void push();

	public void actionPerformed(ActionEvent ae) {
		push();
	}
}
