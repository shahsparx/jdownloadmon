package jdownloadmon.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * An abstract class for defining some default behavoir used across different frames.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadFrame extends JFrame {

	/**
	 * Construct a download frame.
	 * @param title The title of the download frame.
	 */
	public DownloadFrame(String title) {
		super(title);
		init();
	}

	/**
	 * Construct a download frame.
	 */
	public DownloadFrame() {
		super();
		init();
	}

	/**
	 * Initialize the download frame with an icon and sets some default variables.
	 */
	private void init() {
		setIconImage(IconStore.INSTANCE.getImageIcon("logo.png").getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void pack() {
		super.pack();
		// Place the frame in the middle of the screen.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) dim.getWidth() / 2 - getWidth() / 2,
				(int) dim.getHeight() / 2 - getHeight() / 2);
	}
}
