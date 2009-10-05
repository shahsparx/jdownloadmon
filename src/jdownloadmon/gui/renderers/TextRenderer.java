package jdownloadmon.gui.renderers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * Renders a cell with just text in it in a JTable cell.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class TextRenderer extends DownloadRenderer {
	
	/** The text displayed using this renderer. */
	private String mText;

	/**
	 * Construct a text renderer.
	 * @param text The text displayed using this renderer.
	 */
	public TextRenderer(String text) {
		mText = text;
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	/**
	 * Set the text.
	 * @param text The text displayed using this renderer.
	 */
	public void setDisplayText(String text) {
		mText = text;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	@Override
	public String toString() {
		return mText;
	}
}
