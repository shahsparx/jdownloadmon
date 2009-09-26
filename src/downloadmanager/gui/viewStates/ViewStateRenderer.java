package downloadmanager.gui.viewStates;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ViewStateRenderer extends DefaultTableCellRenderer {
	
	private ViewState mViewState;
	
	public ViewStateRenderer(ViewState state) {
		mViewState = state;
	}
	
	public void setViewState(ViewState state) {
		mViewState = state;	
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		ViewStateRenderer v = (ViewStateRenderer) value;
		setViewState(v.mViewState);
		setIcon(mViewState.getImageIcon());
		setText(mViewState.toString());
		return this;
	}

	public String toString() {
		return mViewState.toString();
	}

}
