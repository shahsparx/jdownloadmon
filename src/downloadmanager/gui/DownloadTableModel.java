package downloadmanager.gui;

import javax.swing.table.DefaultTableModel;

/**
 * A Table model for ZE DOWNLOADZ
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadTableModel extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
