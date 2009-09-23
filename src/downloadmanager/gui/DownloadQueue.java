package downloadmanager.gui;

import downloadmanager.DownloadEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue extends JTable {

	/** The list of download views. */
	private ArrayList<DownloadView> mDownloads;
	/** The table model for this table */
	DefaultTableModel mModel;

	/**
	 * Construct a download queue.
	 */
	public DownloadQueue(DefaultTableModel model) {
		super(model);
		mDownloads = new ArrayList<DownloadView>();
		mModel = model;
		mModel.addColumn("File name");
		mModel.addColumn("Progress");
		getColumnModel().getColumn(1).setCellRenderer(new JProgressBarCell());
	}

	/**
	 * Add a download to the list.
	 * @param downloadComponent The download view to add.
	 */
	public void addDownload(DownloadView downloadView) {
		mDownloads.add(downloadView);
		Object[] rowData = { downloadView.getFileName() , downloadView.getProgressBar() };
		mModel.addRow(rowData);
		downloadView.getProgressBar();
	}

	/**
	 * Update a download with the specified event.
	 * @param downloadEvent The download event that was performed.
	 */
	public void update(DownloadEvent downloadEvent) {
	}
}
