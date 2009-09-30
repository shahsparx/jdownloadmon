package downloadmanager.gui;

import downloadmanager.DownloadManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * A button wraps a {@link JButton} and uses its {@link #push() push} method to perform actions in a {@link downloadmanager.DownloadManager}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class Button implements ActionListener {

	/** The JButton wrapped in this button. */
	protected JButton mButton;

	/**
	 * Construct a button.
	 * @param button The JButton used in this button.
	 */
	protected Button(JButton button) {
		mButton = button;
		mButton.addActionListener(this);
	}

	/**
	 * Performs a specific action in the download manager, depending on what button this is.
	 */
	public abstract void push();

	public void actionPerformed(ActionEvent ae) {
		push();
	}
}
