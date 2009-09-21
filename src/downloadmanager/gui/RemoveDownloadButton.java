package downloadmanager.gui;

import downloadmanager.DownloadManager;
import javax.swing.JButton;

/**
 * The class for performing the action when the remove download button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class RemoveDownloadButton extends Button {

    /**
     * Construct an add download button.
     * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
     */
    public RemoveDownloadButton(JButton button, DownloadManager downloadManager) {
        super(button, downloadManager);
    }
    
    @Override
    public void push() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
