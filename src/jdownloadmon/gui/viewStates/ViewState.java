package jdownloadmon.gui.viewStates;

import jdownloadmon.gui.IconStore;
import javax.swing.ImageIcon;

/**
 * A view state is used to display a downloadObject's status state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class ViewState {

	/**
	 * Get the icon that represents this view state.
	 * @return The ImageIcon that represents this view state.
	 */
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
	public abstract String toString();

	/**
	 * @return The text that is to be displayed on the tooltip.
	 */
	public String getToolTipText() {
		return toString();
	}
}
