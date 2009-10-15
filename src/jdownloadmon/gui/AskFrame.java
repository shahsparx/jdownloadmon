package jdownloadmon.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import jdownloadmon.DownloadManager.DefaultFileExistsBehavior;
import jdownloadmon.DownloadObject;

/**
 * A file already exists ask frame, for setting behavior.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AskFrame extends DownloadFrame implements ActionListener {

	/** The download object to perform actions on. */
	private DownloadObject mObject;

	/**
	 * Construct an ask frame.
	 * @param file The filename of the file that already exists.
	 * @param object The associated download object.
	 */
	public AskFrame(String file, DownloadObject object) {
		super("File exists:" + file);
		mObject = object;
		Container content = getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		if (file.length() > 18) {
			file = file.substring(0, 15) + "...";
		}

		JLabel info = new JLabel("File " + file + " already exists. Choose action." );
		info.setBorder(new EmptyBorder(5,5,5,5));
		info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JPanel buttons = new JPanel();
		for (DefaultFileExistsBehavior behavior : DefaultFileExistsBehavior.values()) {
			if (! behavior.equals(DefaultFileExistsBehavior.ASK)) {
				JButton button = new JButton(behavior.toString());
				buttons.add(button);
				button.addActionListener(this);
			}
		}

		content.add(info);
		content.add(buttons);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		mObject.setBehavior(DefaultFileExistsBehavior.valueOf(e.getActionCommand()));
		mObject.download();
		dispose();
	}

	@Override
	public void dispose() {
		mObject.setHasBehavior(false);
		mObject = null;
		super.dispose();
	}

}
