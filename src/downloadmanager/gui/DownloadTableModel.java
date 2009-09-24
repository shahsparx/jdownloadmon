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

	/**
	 * Convert a download view into a table row with data and add it.
	 * @param downloadView The download view to add.
	 */
	public void addDownloadView(DownloadView downloadView) {
		Object[] rowData = { downloadView.getFileName(), downloadView.getViewState(), downloadView };
		addRow(rowData);
	}
}
