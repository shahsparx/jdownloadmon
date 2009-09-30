package downloadmanager.gui.renderers;

import downloadmanager.gui.viewStates.*;
import java.awt.Component;
import javax.swing.JTable;

/**
 * A view state renderer renders a view state in a JTable.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ViewStateRenderer extends DownloadRenderer {

	/** The view state wrapped in this renderer. */
	private ViewState mViewState;

	/**
	 * Construct a view state renderer.
	 * @param state The view state to render.
	 */
	public ViewStateRenderer(ViewState state) {
		mViewState = state;
	}

	/**
	 * Set the view state to a new view state.
	 * @param state The view state to set to.
	 */
	public void setViewState(ViewState state) {
		mViewState = state;
	}

	/**
	 * @return The wrapped view state.
	 */
	public ViewState getViewState() {
		return mViewState;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// set this viewstate to be the values view state and then set the icon and text of the new view state).
		ViewStateRenderer v = (ViewStateRenderer) value;
		setViewState(v.mViewState);
		setIcon(mViewState.getImageIcon());
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setToolTipText(mViewState.getToolTipText());
		return this;
	}

	@Override
	public String toString() {
		return mViewState.toString();
	}
}
