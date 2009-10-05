package jdownloadmon;

import jdownloadmon.gui.GUI;

/**
 * Main class for running the application.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DownloadManager.INSTANCE.init();
		GUI.INSTANCE.init();
	}
}
