package jdownloadmon.gui.buttons;

import jdownloadmon.gui.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * A wrapper class for buttons that create single JFrames.
 * @author Edward Larsson
 */
public abstract class FrameButton extends Button {

	/** The frame that's created. */
	protected DownloadFrame mFrame;

	/**
	 * Construct a frame button.
	 * @param button The wrapped button.
	 * @param title The title of the JFrame that gets created.
	 */
	protected FrameButton(JButton button, String title) {
		super(button);
		mFrame = new DownloadFrame(title);
		mFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void push() {
		mFrame.setVisible(true);
	}

}
