package downloadmanager;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A download logger wrapping a {@link java.util.logging.Logger} used for logging various exceptions to file.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadLogger {

	/** The logger's name */
	private static final String LOGGER_NAME = "DownloadLog";
	/** The wrapped logger. */
	public static final Logger LOGGER = Logger.getLogger(LOGGER_NAME);

	static {
		try {
			boolean append = false;
			FileHandler fh = new FileHandler("DownloadLog.log", append);
			//fh.setFormatter(new XMLFormatter());
			fh.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fh);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
