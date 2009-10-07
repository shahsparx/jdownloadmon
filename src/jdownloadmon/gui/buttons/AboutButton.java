package jdownloadmon.gui.buttons;

import java.awt.Dimension;
import jdownloadmon.gui.*;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Wrapping the behavior of the about menu item that creates an About frame.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class AboutButton extends FrameButton {

	/**
	 * Construct an about menu button.
	 * @param button The JButton wrapped.
	 */
	public AboutButton(JButton button) {
		super(button, "About");

		button.setToolTipText("about");
		button.setFocusPainted(false);

		JPanel allPanel = new JPanel(new FlowLayout());
		JLabel imageLabel = new JLabel(IconStore.INSTANCE.getImageIcon("jdownloadmon.png"));

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new BlueLabel("jDownloadMon");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		JLabel progInfo = new BlueLabel("<html><center>" +
				"A simple download manager that currently only supports http links." +
				"</center></html>");
		// 30 characters for html tags, 38 is current length of the rightPanel
		int fontHeight = progInfo.getFontMetrics(progInfo.getFont()).getHeight();
		int height = (progInfo.getText().length() - 30)  / 38 * fontHeight + fontHeight;
		progInfo.setPreferredSize(new Dimension(0,height));
		progInfo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		progInfo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel tablePanel = new JPanel(new FlowLayout());
		JPanel descriptorPanel = new JPanel();
		descriptorPanel.setLayout(new BoxLayout(descriptorPanel, BoxLayout.Y_AXIS));
		JPanel elementsPanel = new JPanel();
		elementsPanel.setLayout(new BoxLayout(elementsPanel, BoxLayout.Y_AXIS));
		descriptorPanel.add(new BlueLabel("Version:"));
		elementsPanel.add(new BlueLabel("0.1"));
		descriptorPanel.add(new BlueLabel("Author:"));
		elementsPanel.add(new BlueLabel("Edward Larsson"));
		descriptorPanel.add(new BlueLabel("Email:"));
		elementsPanel.add(new BlueLabel("edward.larsson@gmx.com"));
		descriptorPanel.add(new BlueLabel("Icons by:"));
		elementsPanel.add(new BlueLabel("sekkyumu.deviantart.com"));


		tablePanel.add(descriptorPanel);
		tablePanel.add(elementsPanel);

		rightPanel.add(titleLabel);
		rightPanel.add(progInfo);
		rightPanel.add(tablePanel);
		allPanel.add(imageLabel);
		allPanel.add(rightPanel);

		mFrame.getContentPane().add(allPanel);
		mFrame.pack();
	}
}
