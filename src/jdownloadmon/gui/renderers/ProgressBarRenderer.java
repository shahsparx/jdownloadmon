package jdownloadmon.gui.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 * A progress bar renderer renders a JProgressBar in a JTable.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ProgressBarRenderer extends DownloadRenderer {

	/** The progress bar wrapped in this renderer. */
	private JProgressBar mProgressBar;

	/**
	 * Construct the progress bar renderer.
	 */
	public ProgressBarRenderer() {
		mProgressBar = new JProgressBar(0, 100);
		mProgressBar.setBorder(new EmptyBorder(1, 1, 1, 1));
		mProgressBar.setBorderPainted(false);
		mProgressBar.setStringPainted(true);
		mProgressBar.setForeground(Color.getHSBColor(0.5f, 0.5f, 0.5f));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		mProgressBar = ((ProgressBarRenderer) value).mProgressBar;
		mProgressBar.setToolTipText(mProgressBar.getString());
		if (isSelected) {
			mProgressBar.setBackground(table.getSelectionBackground());
		} else {
			mProgressBar.setBackground(table.getBackground());
		}

		return mProgressBar;
	}

	/**
	 * Set the progress bar value.
	 * @param percentage The percentage to set it to.
	 */
	public void setProgressBarValue(int percentage) {
		mProgressBar.setValue(percentage);
	}

	@Override
	public String toString() {
		return mProgressBar.getString();
	}

	@Override
	public int compareTo(DownloadRenderer o) {
		if (o instanceof ProgressBarRenderer) {
			return (int) (mProgressBar.getValue() - ((ProgressBarRenderer) o).mProgressBar.getValue());
		}

		return super.compareTo(o);
	}
}
