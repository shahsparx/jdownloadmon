package downloadmanager.gui;

import downloadmanager.DownloadManager;
import javax.swing.JButton;

/**
 * The add download button is the button in the add download dialog box and is for
 * performing the action when the add download button is pushed.
 * @see AddDownloadBox
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AddDownloadButton extends Button {

    private AddDownloadBox mAddDownloadBox;

    /**
     * Construct an add download button.
     * @param addDownloadBox The add download box this button resides in.
     * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
     */
    public AddDownloadButton(JButton button, DownloadManager downloadManager, AddDownloadBox addDownloadBox) {
        super(button, downloadManager);
        mAddDownloadBox = addDownloadBox;
    }

    @Override
    public void push() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
