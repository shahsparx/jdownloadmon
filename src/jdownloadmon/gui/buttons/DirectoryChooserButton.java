/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdownloadmon.gui.buttons;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import jdownloadmon.DownloadManager;

/**
 * A button that produces a dialog to choose a directory.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DirectoryChooserButton extends Button {

	/** The text field the chosen directory will be written upon. */
	private JTextField directoryField;

	/**
	 * Construct a directory chooser button.
	 * @param button The JButton that when clicked, opens a dialog for a directory chooser.
	 * @param field The text field which will have the chosen directory (as a String) written to it.
	 */
	public DirectoryChooserButton(JButton button, JTextField field) {
		super(button);
		button.setToolTipText("Browse..");
		directoryField = field;
	}

	@Override
	public void push() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(DownloadManager.INSTANCE.getSettings().getDefaultDirectory()));
		chooser.setDialogTitle("Choose a directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// disable the "All files" option.
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			directoryField.setText("" + chooser.getSelectedFile());
		}
	}
}
