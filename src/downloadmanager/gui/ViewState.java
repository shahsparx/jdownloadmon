package downloadmanager.gui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class ViewState extends DefaultTableCellRenderer {

	/**
	 * Get an image...
	 * @return The image
	 */
	//TODO make this (or preferrably outside class) a store with each image stored only once in a hashmap.
	public ImageIcon getImageIcon() {		
		return IconStore.INSTANCE.getImageIcon(getFilename());
	}

	/**
	 * Get the filename + extension of the image file that represents this viewstate.
	 * @return The filename and extension of this viewstate's image file.
	 */
	public String getFilename() {
		return toString() + ".png";
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		ViewState v = (ViewState) value;
		setIcon(v.getImageIcon());
		setText(v.toString());
		return this;
	}

	@Override
	public abstract String toString();

	//TODO inside DownloadObjects run method is a check to see if download is completed and then run changeState which notifies listeners.
	//TODO Use if else if for now to determine which viewstate to use with which statustate on downloadStateevent.
}
