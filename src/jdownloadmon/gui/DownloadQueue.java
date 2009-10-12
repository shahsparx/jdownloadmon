package jdownloadmon.gui;

import jdownloadmon.DownloadManager;
import jdownloadmon.events.DownloadConnectedEvent;
import jdownloadmon.gui.viewStates.CompletedViewState;
import jdownloadmon.gui.viewStates.ErrorViewState;
import jdownloadmon.gui.viewStates.ActiveViewState;
import jdownloadmon.gui.viewStates.PendingViewState;
import jdownloadmon.gui.viewStates.InactiveViewState;
import jdownloadmon.states.ActiveState;
import jdownloadmon.states.CompletedState;
import jdownloadmon.events.DownloadProgressEvent;
import jdownloadmon.DownloadObject;
import jdownloadmon.events.DownloadStatusStateEvent;
import jdownloadmon.gui.renderers.ProgressBarRenderer;
import jdownloadmon.gui.renderers.TextRenderer;
import jdownloadmon.gui.renderers.ViewStateRenderer;
import jdownloadmon.gui.viewStates.ViewState;
import jdownloadmon.states.ErrorState;
import jdownloadmon.states.InactiveState;
import jdownloadmon.states.PendingState;
import jdownloadmon.states.StatusState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumn;
import jdownloadmon.gui.renderers.ValueRenderer;

/**
 * A download queue for the gui to show the list of download objects.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadQueue extends JTable implements ActionListener {

	/** The list of download views. */
	private HashMap<DownloadObject, DownloadView> mDownloadViews;
	/** The table model for this table */
	private DownloadTableModel mTableModel;
	/** Boolean value representing whether the selection is currently being adjusted. */
	private boolean mIsAdjusting;

	/**
	 * Construct a download queue.
	 * @param model The default table model to use with this table.
	 */
	public DownloadQueue(DownloadTableModel model) {
		super(model);
		mTableModel = model;
		mDownloadViews = new HashMap<DownloadObject, DownloadView>();

		// Sorting listener.
		getTableHeader().addMouseListener(mTableModel.new ColumnListener(this));

		// Make the columns renderable.
		TableColumn position = columnModel.getColumn(columnModel.getColumnIndex("#"));
		position.setCellRenderer(new ValueRenderer(null, 0));
		position.setMaxWidth(25);
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Filename.toString())).setCellRenderer(new TextRenderer(null));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Size.toString())).setCellRenderer(new ValueRenderer(null, 0));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.ETA.toString())).setCellRenderer(new ValueRenderer(null, 0));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Speed.toString())).setCellRenderer(new ValueRenderer(null, 0));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Status.toString())).setCellRenderer(new ViewStateRenderer(null));
		columnModel.getColumn(columnModel.getColumnIndex(DownloadTableModel.Columns.Progress.toString())).setCellRenderer(new ProgressBarRenderer());

		// Double click to open file.
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = rowAtPoint(e.getPoint());
					mTableModel.getViewAt(row).getDownloadObject().openFile();
				}
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		mIsAdjusting = e.getValueIsAdjusting();
	}
	/**
	 * Sort the table whilst keeping the selections.
	 * Synchronized to keep the selection intact if multiple threads try to access the selected rows at the same time.
	 */
	public synchronized void sort() {
		if (mTableModel.isSorting() &! mIsAdjusting) {
			GUI.INSTANCE.setThinkingCursor();
			DownloadView[] views = getSelectedViews();
			mTableModel.sortRows();
			mTableModel.fireTableDataChanged();
			selectViews(views);
			GUI.INSTANCE.setDefaultCursor();
		}
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
		view.updateETA();
		view.updateSpeed();
		sort();
		this.repaint();
	}

	/**
	 * A download object has connected, so update view with filesize and filename.
	 * @param downloadConnectedEvent The download connection event that was raised.
	 */
	public void update(DownloadConnectedEvent downloadConnectedEvent) {
		DownloadView view = mDownloadViews.get(downloadConnectedEvent.getDownloadObject());
		view.updateSize();
		view.updateFileName();
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
			String message = ((ErrorState) statusState).getErrorMessage();
			view.setViewState(new ErrorViewState(message));
		}

		// Update ETA and speed after the download gets inactivated too otherwise the eta and speed will remain.
		view.updateETA();
		view.updateSpeed();
		view.updateQueuePosition();
		updateQueueingPositions();
		this.repaint();
	}

	/**
	 * Update the queue position numbers of active and pending download views.
	 */
	public void updateQueueingPositions() {
		for (DownloadView view : mDownloadViews.values()) {
			ViewState state = view.getViewStateRenderer().getViewState();
			if (state instanceof ActiveViewState || state instanceof PendingViewState) {
				view.updateQueuePosition();
			}
		}

		sort();
	}

	/**
	 * Get the download views that are selected;
	 * @return An array of the download views that are currently selected.
	 */
	public DownloadView[] getSelectedViews() {
		DownloadView[] views = new DownloadView[getSelectedRows().length];
		int i = 0;
		for (int row : getSelectedRows()) {
			DownloadView view = mTableModel.getViewAt(row);
			views[i] = view;
			i++;
		}
		return views;
	}

	/**
	 * Select the download views supplied.
	 * @param views Which views to select.
	 */
	public void selectViews(DownloadView[] views) {
		for (DownloadView view : views) {
			int row = mTableModel.getRowForView(view);
			this.addRowSelectionInterval(row, row);
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
		GUI.INSTANCE.setThinkingCursor();

		DownloadView[] views = getSelectedViews();
		Object source = e.getSource();
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
				DownloadManager.INSTANCE.removeDownload(downloadObject);
				mTableModel.removeDownloadView(row);
			}
			/*
			 * The following two loops for moving downloads up and down the queue make sure that
			 * when multiple downloads are selected they move in sync. For example:
			 * If 1, 2 and 3 are selected and the user tries to move them up the queue nothing happens.
			 */
		} else if (source.equals(GUI.INSTANCE.getMoveUpQueueButton())) {
			int previousPos = 0;
			Arrays.sort(views, new QueueComparator<DownloadView>());
			for (DownloadView view : views) {
				if (view.getQueuePosition() != Integer.MAX_VALUE) {
					previousPos++;
					int pos = view.getQueuePosition();
					if (pos != previousPos) {
						DownloadObject downloadObject = view.getDownloadObject();
						if (downloadObject.getStatusState() instanceof ActiveState) {
							DownloadManager.INSTANCE.moveActiveUp(downloadObject);
						} else if (downloadObject.getStatusState() instanceof PendingState) {
							DownloadManager.INSTANCE.movePendingUp(downloadObject);
						}
					}
				}
			}
			updateQueueingPositions();
		} else if (source.equals(GUI.INSTANCE.getMoveDownQueueButton())) {
			int previousPos = DownloadManager.INSTANCE.getNumberOfQueuedDownloads() + 1;
			Arrays.sort(views, new QueueComparator<DownloadView>());
			for (int i = views.length - 1; i >= 0; i--) {
				DownloadView view = views[i];
				if (view.getQueuePosition() != Integer.MAX_VALUE) {
					previousPos--;
					int pos = view.getQueuePosition();
					if (pos != previousPos) {
						DownloadObject downloadObject = view.getDownloadObject();
						if (downloadObject.getStatusState() instanceof ActiveState) {
							DownloadManager.INSTANCE.moveActiveDown(downloadObject);
						} else if (downloadObject.getStatusState() instanceof PendingState) {
							DownloadManager.INSTANCE.movePendingDown(downloadObject);
						}
					}
				}
			}
			updateQueueingPositions();
		}
		GUI.INSTANCE.setDefaultCursor();
	}
}
