package downloadmanager.gui;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * A JProgressBar renderable in a JTable.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class JProgressBarCell extends JProgressBar implements TableCellRenderer {
	
	/**
	 * @see TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int) 
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}
}
