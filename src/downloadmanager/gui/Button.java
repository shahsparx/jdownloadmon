package downloadmanager.gui;

import downloadmanager.DownloadManager;
import javax.swing.JButton;

/**
 * A button wraps a {@link JButton} and uses its {@link #push() push} method to perform actions in a {@link downloadmanager.DownloadManager}.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class Button {

    /** The JButton wrapped in this button. */
    protected JButton mButton;
    /** The Download Manager to perform actions on with this button. */
    protected DownloadManager mDownloadManager;

    /**
     * Construct a button.
     * @param button The JButton used in this button.
     * @param downloadManager The download manager to perform actions on.
     */
    protected Button(JButton button, DownloadManager downloadManager) {
	mButton = button;
	mDownloadManager = downloadManager;
    }

    /**
     * Performs a specific action in the download manager, depending on what button this is.
     */
    public abstract void push();
}
