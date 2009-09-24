package downloadmanager.gui;

import downloadmanager.ActiveState;
import downloadmanager.CompletedState;
import downloadmanager.DownloadProgressEvent;
import downloadmanager.DownloadObject;
import downloadmanager.DownloadStatusStateEvent;
import downloadmanager.ErrorState;
import downloadmanager.InactiveState;
import downloadmanager.PendingState;
import downloadmanager.StatusState;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue extends JTable {

	/** The list of download views. */
	private HashMap<DownloadObject, DownloadView> mDownloadViews;
	/** The table model for this table */
	DownloadTableModel mTableModel;

	/**
	 * The columns in this table.
	 */
	private enum Columns {
		/** Filename column. */
		Filename,
		/** Status state icon column. */
		Status,
		/** Progress bar column. */
		Progress;
	}

	/**
	 * Construct a download queue.
	 * @param model The default table model to use with this table.
	 */
	public DownloadQueue(DownloadTableModel model) {
		super(model);
		mTableModel = model;
		mDownloadViews = new HashMap<DownloadObject, DownloadView>();
		mTableModel.setColumnIdentifiers(Columns.values());
		//set the progress column so that a progressbar is renderable in it.
		columnModel.getColumn(columnModel.getColumnIndex(Columns.Progress.toString())).setCellRenderer(new DownloadView(null, null));
		columnModel.getColumn(columnModel.getColumnIndex(Columns.Status.toString())).setCellRenderer(new InactiveViewState());
	}

	/**
	 * Add a download view to the table.
	 * @param downloadComponent The download view to add.
	 */
	public void addDownloadView(DownloadView downloadView) {
		mDownloadViews.put(downloadView.getDownloadObject(), downloadView);
		mTableModel.addDownloadView(downloadView);
	}

	/**
	 * Remove a download view from the table.
	 * @param downloadComponent The download view to remove.
	 */
	public void removeDownload(DownloadView downloadView) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Update a download with the specified download event.
	 * @param downloadProgressEvent The download event that was performed.
	 */
	public void update(DownloadProgressEvent downloadProgressEvent) {
		DownloadView view = mDownloadViews.get(downloadProgressEvent.getDownloadObject());
		view.setProgressBarValue(downloadProgressEvent.getPercentDownloaded());
	}

	/**
	 * Update a download with the specified download event.
	 * @param downloadStatusStateEvent The download event that was performed.
	 */
	void update(DownloadStatusStateEvent downloadStatusStateEvent) {
		DownloadView view = mDownloadViews.get(downloadStatusStateEvent.getDownloadObject());
		StatusState statusState = downloadStatusStateEvent.getStatusState();
		if (statusState instanceof ActiveState) {
			view.setViewState(new ActiveViewState());
		} else if (statusState instanceof InactiveState) {
			view.setViewState(new InactiveViewState());
		} else if (statusState instanceof PendingState) {
			view.setViewState(new PendingViewState());
		} else if (statusState instanceof CompletedState) {
			view.setViewState(new CompletedViewState());
		} else if (statusState instanceof ErrorState) {
			view.setViewState(new ErrorViewState());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		if (!e.getValueIsAdjusting()) {
			int column = columnModel.getColumnIndex(Columns.Progress.toString());
			for (int row : getSelectedRows()) {
				DownloadView view = (DownloadView) getValueAt(row, column);
				view.setProgressBarValue((int)(100*Math.random()));
			}
		}
		this.repaint();
	}

	/**
	 * Get the download view linked to a certain download object.
	 * @param The download object linked to the download view.
	 * @return downloadView The download view linked to the download object.
	 */
	public DownloadView getDownloadView(DownloadObject downloadObject) {
		return mDownloadViews.get(downloadObject);
	}
}
