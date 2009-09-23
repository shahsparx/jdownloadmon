package downloadmanager.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The class for performing the action when the new download button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class NewDownloadButton extends Button {

    /**
     * Construct a new download button.
     * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
     */
    public NewDownloadButton(JButton button) {
        super(button);
    }

    @Override
    public void push() {
		JFrame frame = new JFrame("New Download");
		Container content = frame.getContentPane();
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());
		JButton addButton = new JButton("add");
		JTextField urlField = new JTextField(15);
		center.add(urlField);
		center.add(addButton);
		content.add(center, BorderLayout.CENTER);
		frame.setLocation(220, 300);
		frame.pack();
		frame.setVisible(true);
		
        AddDownloadBox box = new AddDownloadBox(urlField);
		new AddDownloadButton(addButton, box, frame);
    }
}
