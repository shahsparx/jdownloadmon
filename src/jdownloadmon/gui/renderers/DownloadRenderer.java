package jdownloadmon.gui.renderers;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The download renderer class sets default things like what text to display in the cell
 * and what color the selected/deselected background should be.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class DownloadRenderer extends DefaultTableCellRenderer {

	/**
	 * Protected constructor to set some default behavior for cell renderers.
	 */
	protected DownloadRenderer() {
		Font font = new Font("Arial", Font.PLAIN, 12);
		setFont(font);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		DownloadRenderer renderer = (DownloadRenderer) value;
		setText(renderer.toString());
		setToolTipText(renderer.toString());
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}

		return this;
	}

	@Override
	public abstract String toString();
}
