package downloadmanager.gui.viewStates;

import downloadmanager.gui.*;
import javax.swing.ImageIcon;


/**
 *
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class ViewState {

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
	public abstract String toString();

	/**
	 * @return The text that is to be displayed on the tooltip.
	 */
	public String getToolTipText() {
		return toString();
	}

	//TODO inside DownloadObjects run method is a check to see if download is completed and then run changeState which notifies listeners.
	//TODO Use if else if for now to determine which viewstate to use with which statustate on downloadStateevent.
}
