package jdownloadmon.gui.buttons;

import jdownloadmon.gui.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
	}

	@Override
	public void push() {
		FlowLayout flow = new FlowLayout(SwingConstants.RIGHT);

		JPanel urlPanel = new JPanel(flow);
		JPanel directoryPanel = new JPanel(flow);
		JPanel buttons = new JPanel(new FlowLayout());
		
		JTextField urlField = new JTextField(40);
		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		urlPanel.add(urlLabel);
		urlPanel.add(urlField);

		JTextField directoryField = new JTextField(40);
		JLabel directoryLabel = new JLabel("Directory:");
		directoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		directoryPanel.add(directoryLabel);
		directoryPanel.add(directoryField);
		
		JButton addButton = new JButton(IconStore.INSTANCE.getImageIcon("add.png"));
		buttons.add(addButton);

		JPanel center = new JPanel();
		center.setBorder(new EmptyBorder(5,5,5,5));
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		center.add(urlPanel);
		center.add(directoryPanel);
		center.add(buttons);
		DownloadFrame frame = new DownloadFrame("New Download");
		Container content = frame.getContentPane();
		content.add(center, BorderLayout.CENTER);

		AddDownloadBox box = new AddDownloadBox(urlField, directoryField);
		new AddDownloadButton(addButton, box, frame);
	}
}
