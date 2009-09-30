package downloadmanager.gui;

import downloadmanager.DownloadManager;
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
import downloadmanager.gui.renderers.FilenameRenderer;
import downloadmanager.gui.renderers.PositionRenderer;
import downloadmanager.gui.renderers.ProgressBarRenderer;
import downloadmanager.gui.renderers.ViewStateRenderer;
import downloadmanager.gui.viewStates.ViewState;
import downloadmanager.states.ErrorState;
import downloadmanager.states.InactiveState;
import downloadmanager.states.PendingState;
import downloadmanager.states.StatusState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

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
	 * Construct a download queue.
	 * @param model The default table model to use with this table.
	 */
	public DownloadQueue(DownloadTableModel model) {
		super(model);
		mTableModel = model;
		mDownloadViews = new HashMap<DownloadObject, DownloadView>();
		//set the progress column so that a progressbar is renderable in it.
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Progress.toString())).setCellRenderer(new ProgressBarRenderer());
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Status.toString())).setCellRenderer(new ViewStateRenderer(new InactiveViewState()));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Filename.toString())).setCellRenderer(new FilenameRenderer(""));
		TableColumn position = columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Position.toString()));
		position.setCellRenderer(new PositionRenderer(""));
		position.setHeaderValue("#");
		position.setMaxWidth(20);
	}

	/**
	 * Add a download view to the table.
	 * @param downloadView The download view to add.
	 */
	public void addDownloadView(DownloadView downloadView) {
		mDownloadViews.put(downloadView.getDownloadObject(), downloadView);
		mTableModel.addDownloadView(downloadView);
	}

	/**
	 * Remove a download view from the table.
	 * @param downloadObject The download object linked to the download view to remove.
	 */
	public void removeDownloadView(DownloadObject downloadObject) {
		mDownloadViews.remove(downloadObject);
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
			view.setViewState(new ErrorViewState(((ErrorState) statusState).getErrorMessage()));
		}

		view.updateQueuePosition();
		updateQueuePositions();
		this.repaint();
	}

	/**
	 * Update the queue position numbers of active and pending download views.
	 */
	public void updateQueuePositions() {
		for (DownloadView view : mDownloadViews.values()) {
			ViewState state = view.getViewStateRenderer().getViewState();
			if (state instanceof ActiveViewState || state instanceof PendingViewState) {
				view.updateQueuePosition();
			}
		}
	}

	/**
	 * Get the download view linked to a certain download object.
	 * @param downloadObject The download object linked to the download view.
	 * @return downloadView The download view linked to the download object.
	 */
	public DownloadView getDownloadView(DownloadObject downloadObject) {
		return mDownloadViews.get(downloadObject);
	}

	/**
	 * A button was pushed. Perform the appropriate action on selected rows.
	 *
	 * @param e The action event with info about which button was pushed, among others.
	 */
	public void actionPerformed(ActionEvent e) {
		DownloadView[] views = new DownloadView[getSelectedRows().length];
		int i = 0;

		Object source = e.getSource();
		for (int row : getSelectedRows()) {
			DownloadView view = mTableModel.getViewAt(row);
			views[i] = view;
			i++;
		}

		if (source.equals(GUI.INSTANCE.getStartButton())) {
			for (DownloadView view : views) {
				view.getDownloadObject().download();
			}
		} else if (source.equals(GUI.INSTANCE.getStopButton())) {
			for (DownloadView view : views) {
				view.getDownloadObject().pause();
			}
		} else if (source.equals(GUI.INSTANCE.getRemoveButton())) {
			while (getSelectedRowCount() > 0) {
				int row = getSelectedRow();
				DownloadView view = mTableModel.getViewAt(row);
				DownloadObject downloadObject = view.getDownloadObject();
				downloadObject.remove();
				removeDownloadView(downloadObject);
				mTableModel.removeDownloadView(row);
			}
		} else if (source.equals(GUI.INSTANCE.getMoveUpQueueButton())) {
			for (DownloadView view : views) {

				DownloadObject downloadObject = view.getDownloadObject();
				if (downloadObject.getStatusState() instanceof ActiveState) {
					DownloadManager.INSTANCE.moveActiveUp(downloadObject);
				} else if (downloadObject.getStatusState() instanceof PendingState) {
					DownloadManager.INSTANCE.movePendingUp(downloadObject);
				}
			}

			/* if (getSelectedRow() != 0) {
			for (int row : getSelectedRows()) {
			mTableModel.moveRowUp(row);
			}
			for (int row : getSelectedRows()) {
			this.removeRowSelectionInterval(row, row);
			this.addRowSelectionInterval(row - 1, row - 1);
			}
			}*/
		} else if (source.equals(GUI.INSTANCE.getMoveDownQueueButton())) {
			for (DownloadView view : views) {
				DownloadObject downloadObject = view.getDownloadObject();
				if (downloadObject.getStatusState() instanceof ActiveState) {
					DownloadManager.INSTANCE.moveActiveDown(downloadObject);
				} else if (downloadObject.getStatusState() instanceof PendingState) {
					DownloadManager.INSTANCE.movePendingDown(downloadObject);
				}
			}
		}

		updateQueuePositions();
	}
}
