package jdownloadmon.gui.buttons;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;
import jdownloadmon.gui.AddDownloadBox;
import jdownloadmon.gui.DownloadFrame;
import jdownloadmon.gui.IconStore;

/**
 * The class for performing the action when the new download button is pushed.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class NewDownloadButton extends Button {

	/**
	 * Construct a new download button.
	 * @param button The JButton wrapped in this button.
	 * @see Button#Button(javax.swing.JButton)
	 */
	public NewDownloadButton(JButton button) {
		super(button);
		button.setToolTipText("new");
	}

	@Override
	public void push() {
		FlowLayout flow = new FlowLayout(SwingConstants.LEFT);

		JPanel urlPanel = new JPanel(flow);
		JPanel directoryPanel = new JPanel(flow);
		JPanel buttons = new JPanel(new FlowLayout());

		JTextField urlField = new JTextField(48);
		try {
			// fetch clipboard into url field.
			String clipboard = (String)Toolkit.getDefaultToolkit().getSystemClipboard().
					getContents(urlField).getTransferData(DataFlavor.stringFlavor);
			if (clipboard.startsWith("http://")) {
				// test for malformed url exception.
				new URL(clipboard);
				urlField.setText(clipboard);
			}
		} catch (Exception e) {
			// ignore
		}

		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setHorizontalAlignment(SwingConstants.LEFT);
		urlPanel.add(urlLabel);
		urlPanel.add(urlField);

		JTextField directoryField = new JTextField(40);
		JLabel directoryLabel = new JLabel("Directory:");
		directoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		JButton browseButton = new JButton("Browse..");
		new DirectoryChooserButton(browseButton, directoryField);
		directoryPanel.add(directoryLabel);
		directoryPanel.add(directoryField);
		directoryPanel.add(browseButton);
		setPopupMenu(urlField);
		setPopupMenu(directoryField);

		JButton addButton = new JButton(IconStore.INSTANCE.getImageIcon("add.png"));
		buttons.add(addButton);

		JPanel center = new JPanel();
		center.setBorder(new EmptyBorder(5, 5, 5, 5));
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		center.add(urlPanel);
		center.add(directoryPanel);
		center.add(buttons);
		DownloadFrame frame = new DownloadFrame("New Download");
		Container content = frame.getContentPane();
		content.add(center, BorderLayout.CENTER);
		frame.setResizable(false);

		AddDownloadBox box = new AddDownloadBox(urlField, directoryField);
		new AddDownloadButton(addButton, box, frame);
	}
}
