package downloadmanager.gui;

import downloadmanager.gui.viewStates.CompletedViewState;
import downloadmanager.gui.viewStates.ErrorViewState;
import downloadmanager.gui.viewStates.ActiveViewState;
import downloadmanager.gui.viewStates.PendingViewState;
import downloadmanager.gui.viewStates.InactiveViewState;
import downloadmanager.states.ActiveState;
import downloadmanager.states.CompletedState;
import downloadmanager.events.DownloadProgressEvent;
import downloadmanager.DownloadObject;
import downloadmanager.events.DownloadStatusStateEvent;
import downloadmanager.gui.viewStates.ViewStateRenderer;
import downloadmanager.states.ErrorState;
import downloadmanager.states.InactiveState;
import downloadmanager.states.PendingState;
import downloadmanager.states.StatusState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JTable;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue extends JTable implements ActionListener {

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
		columnModel.getColumn(columnModel.getColumnIndex(Columns.Status.toString())).setCellRenderer(new ViewStateRenderer(new InactiveViewState()));
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
		this.repaint();
	}

	/**
	 * Update a download with the specified download event.
	 * @param downloadStatusStateEvent The download event that was performed.
	 */
	public void update(DownloadStatusStateEvent downloadStatusStateEvent) {
		DownloadView view = mDownloadViews.get(downloadStatusStateEvent.getDownloadObject());
		StatusState statusState = downloadStatusStateEvent.getNewStatusState();
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

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		DownloadView[] views = new DownloadView[getSelectedRows().length];

		int i = 0;
		int column = columnModel.getColumnIndex(Columns.Progress.toString());
		for (int row : getSelectedRows()) {
			DownloadView view = (DownloadView) getValueAt(row, column);
			views[i] = view;
			i++;
		}

		if (source.equals(GUI.INSTANCE.getStartButton())) {
			for (DownloadView view : views) {
				view.getDownloadObject().download();
			}
		} else if (source.equals(GUI.INSTANCE.getStopButton())) {
			for (DownloadView view : views) {
				view.getDownloadObject().stop();
			}
		} else if (source.equals(GUI.INSTANCE.getRemoveButton())) {
			for (DownloadView view : views) {
				view.getDownloadObject().stop();
				// remove from downloadmanager and table
			}
		} else if (source.equals(GUI.INSTANCE.getMoveUpQueueButton())) {
			for (DownloadView view : views) {
				// move stuff up
			}
		} else if (source.equals(GUI.INSTANCE.getMoveDownQueueButton())) {
			for (DownloadView view : views) {
				// move stuff down
			}
		}
	}
}
