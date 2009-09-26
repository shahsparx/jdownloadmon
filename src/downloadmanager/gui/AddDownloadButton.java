package downloadmanager.gui;

import downloadmanager.DownloadManager;
import downloadmanager.DownloadObject;
import downloadmanager.MalformedURLException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private JFrame mFrame;
	/** The error label that appears if the URL is not valid. */
	private JLabel mErrorLabel;

	/**
	 * Construct an add download button.
	 * @param button The add download JButton.
	 * @param addDownloadBox The add download box this button resides in.
	 */
	public AddDownloadButton(JButton button, AddDownloadBox addDownloadBox, JFrame frame) {
		super(button);
		button.setToolTipText("add");
		button.setFocusPainted(false);
		mAddDownloadBox = addDownloadBox;
		mFrame = frame;
		mAddDownloadBox.getURLTextField().addActionListener(this);
		mErrorLabel = new JLabel("Invalid URL");
		mErrorLabel.setForeground(Color.red);
		mFrame.getContentPane().add(mErrorLabel, BorderLayout.SOUTH);
		mFrame.pack();
		mErrorLabel.setVisible(false);
	}

	@Override
	public void push() {
		try {
			DownloadObject dO = DownloadManager.INSTANCE.addDownload(mAddDownloadBox.getURLTextField().getText());
			GUI.INSTANCE.addDownloadObject(dO);
			mFrame.dispose();
		} catch (MalformedURLException me) {
			mErrorLabel.setText(me.getMessage());
			mErrorLabel.setVisible(true);
		}
	}
}
