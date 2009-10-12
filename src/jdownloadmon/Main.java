package jdownloadmon;

import jdownloadmon.gui.GUI;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.BindException;

/**
 * Main class for running the application.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class Main {

	/**
	 * The start of the application.
	 * @param args Any command line arguments.
	 */
	public static void main(String[] args) {
		// Only allow one instance of download manager to run by using a socket.
		int somePort = 62753;
		try {
			new ServerSocket(somePort);
			DownloadManager.INSTANCE.init();
			GUI.INSTANCE.init();
		} catch (BindException e) {
			// Another process is listening on that port (probably another jdownloadmon).
			DownloadLogger.LOGGER.warning("Can only run one instance at time.");
		} catch (IOException e) {
			// Could not listen on that port for some other reason.
			DownloadLogger.LOGGER.severe(e.toString());
		}
	}
}
