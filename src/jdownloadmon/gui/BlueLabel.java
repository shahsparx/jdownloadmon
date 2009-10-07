package jdownloadmon.gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 * A JLabel with blue text.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class BlueLabel extends JLabel {

	/**
	 * Construct a blue label.
	 * @param text The text this label is to dislay.
	 */
	public BlueLabel(String text) {
		super(text);
		setForeground(Color.BLUE);
		setBorder(new EmptyBorder(2,2,2,2));
	}

}
