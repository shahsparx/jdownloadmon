package downloadmanager.gui.renderers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * The position renderer renders the queue position of a download object.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class PositionRenderer extends DownloadRenderer {

	/** The queue position as a String. */
	private String mQueuePosition;

	/**
	 * Construct a position renderer.
	 * @param queuePosition The queue position to display using this renderer as a String.
	 */
	public PositionRenderer(String queuePosition) {
		mQueuePosition = queuePosition;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setHorizontalAlignment(SwingConstants.RIGHT);
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	/**
	 * Set the queue position.
	 * @param queuePosition The queue position to display using this renderer as a String.
	 */
	public void setQueuePosition(String queuePosition) {
		mQueuePosition = queuePosition;
	}

	@Override
	public String toString() {
		return mQueuePosition;
	}
}
