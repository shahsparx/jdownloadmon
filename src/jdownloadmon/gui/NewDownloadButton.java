package jdownloadmon.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The class for performing the action when the new download button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class NewDownloadButton extends Button {

	/**
	 * Construct a new download button.
	 * @param button The JButton wrapped in this button.
	 * @see Button#Button(javax.swing.JButton, downloadmanager.DownloadManager)
	 */
	public NewDownloadButton(JButton button) {
		super(button);
		button.setToolTipText("new");
		button.setFocusPainted(false);
	}

	@Override
	public void push() {
		JPanel url = new JPanel(new GridLayout(2,2));
		JPanel directory = new JPanel(new GridLayout(2,2));
		JPanel buttons = new JPanel(new FlowLayout());

		JTextField urlField = new JTextField(20);
		JLabel urlLabel = new JLabel("URL:");
		url.add(urlLabel);
		url.add(urlField);

		JTextField directoryField = new JTextField(20);
		JLabel directoryLabel = new JLabel("Directory:");
		directory.add(directoryLabel);
		directory.add(directoryField);
		
		JButton addButton = new JButton(IconStore.INSTANCE.getImageIcon("add.png"));
		buttons.add(addButton);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		center.add(url);
		center.add(directory);
		center.add(buttons);
		JFrame frame = new JFrame("New Download");
		Container content = frame.getContentPane();
		content.add(center, BorderLayout.CENTER);
		frame.setLocation(220, 300);
		frame.pack();
		frame.setVisible(true);

		AddDownloadBox box = new AddDownloadBox(urlField, directoryField);
		new AddDownloadButton(addButton, box, frame);
	}
}
