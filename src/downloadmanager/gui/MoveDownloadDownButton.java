package downloadmanager.gui;

import downloadmanager.DownloadManager;
import javax.swing.JButton;

/**
 * The class for performing the action when the move download down button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class MoveDownloadDownButton extends Button {

    /**
     * Construct a move download down button.
     * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
     */
    public MoveDownloadDownButton(JButton button, DownloadManager downloadManager) {
	super(button, downloadManager);
    }

    @Override
    public void push() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
