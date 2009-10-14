package jdownloadmon.gui.buttons;

import jdownloadmon.DownloadManager;
import jdownloadmon.DownloadObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import jdownloadmon.URLAlreadyExistsException;
import jdownloadmon.gui.AddDownloadBox;
import jdownloadmon.gui.DownloadFrame;
import jdownloadmon.gui.GUI;
import jdownloadmon.states.ActiveState;

/**
 * The add download button is the button in the add download dialog box and is for
 * performing the action when the add download button is pushed.
 * @see AddDownloadBox
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AddDownloadButton extends Button {

	/** The add download box this button is associated with. */
	private AddDownloadBox mAddDownloadBox;
	/** The JFrame this button is associated with. */
	private DownloadFrame mFrame;
	/** The error label that appears if the URL is not valid. */
	private JLabel mErrorLabel;

	/**
	 * Construct an add download button.
	 * @param button The add download JButton.
	 * @param addDownloadBox The add download box this button resides in.
	 * @param frame The JFrame this button is in.
	 */
	public AddDownloadButton(JButton button, AddDownloadBox addDownloadBox, DownloadFrame frame) {
		super(button);
		button.setToolTipText("add");

		mAddDownloadBox = addDownloadBox;
		mFrame = frame;
		mAddDownloadBox.getURLTextField().addActionListener(this);
		mErrorLabel = new JLabel(" ");
		mErrorLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mErrorLabel.setForeground(Color.red);
		mFrame.getContentPane().add(mErrorLabel, BorderLayout.SOUTH);
		mFrame.pack();
		mFrame.setVisible(true);
	}

	@Override
	public void push() {
		try {
			// TODO regex for directory field.
			DownloadObject dO = DownloadManager.INSTANCE.addDownload(mAddDownloadBox.getURLTextField().getText(),
					mAddDownloadBox.getDirectoryTextField().getText());
			GUI.INSTANCE.addDownloadObject(dO);
			dO.changeStatusState(new ActiveState(dO));
			mFrame.dispose();
		} catch (URLAlreadyExistsException e) {
			mErrorLabel.setText(e.getMessage());
		} catch (MalformedURLException e) {
			mErrorLabel.setText(e.getMessage());
		}
	}
}
