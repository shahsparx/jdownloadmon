package downloadmanager.gui;

import java.net.URL;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * An icon store for managing icons and while saving memory usage.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class IconStore {
	
	/** The singleton instance of this store. */
	public static final IconStore INSTANCE = new IconStore();
	/** The hash map containing the icons. */
	private HashMap<String, ImageIcon> mIcons;
	
	/**
	 * Private constructor.
	 */
	private IconStore() {
		mIcons = new HashMap<String, ImageIcon>();
	}
	
	/**
	 * Get an icon from the store.
	 * @param ref The path to the icon.
	 * @return The requested icon.
	 */
	public ImageIcon getImageIcon(String ref) {
		ref = "/images/" + ref;
		// Return the icon from the map if it's already loaded.
		if (mIcons.get(ref) != null) {
			return mIcons.get(ref);
		}

		URL url = getClass().getResource(ref);
		ImageIcon icon = new ImageIcon(url);
		mIcons.put(ref, icon);
		
		return icon;
	}
}
