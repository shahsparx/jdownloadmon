package downloadmanager.gui;

import downloadmanager.DownloadManager;
import javax.swing.JButton;

/**
 * The class for performing the action when the move download up button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class MoveDownloadUpButton extends Button {

    /**
     * Construct a move download up button.
     * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
     */
    public MoveDownloadUpButton(JButton button, DownloadManager downloadManager) {
        super(button, downloadManager);
    }



    @Override
    public void push() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
