package downloadmanager.gui.renderers;

import java.awt.Component;
import javax.swing.JTable;

/**
 * The filename renderer renders the cells of the filename column in a JTable.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class FilenameRenderer extends DownloadRenderer {

	/** The filename displayed using this renderer. */
	private String mFilename;

	/**
	 * Construct a filename renderer.
	 * @param filename The filename displayed using this renderer.
	 */
	public FilenameRenderer(String filename) {
		mFilename = filename;
	}

	/**
	 * Set the filename.
	 * @param filename The filename displayed using this renderer.
	 */
	public void setFilename(String filename) {
		mFilename = filename;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	@Override
	public String toString() {
		return mFilename;
	}
}
