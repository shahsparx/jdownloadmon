package downloadmanager;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class DownloadManagerTest {

	public DownloadManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of addToActiveList method, of class DownloadManager.
	 */
	@Test
	public void testAddToActiveList() {
		System.out.println("addToActiveList");
		DownloadObject downloadObject = null;
		DownloadManager instance = null;
		boolean expResult = false;
		boolean result = instance.addToActiveList(downloadObject);
	    result = instance.addToActiveList(downloadObject);
	    assertEquals(expResult, result);
	}

	/**
	 * Test of addDownload method, of class DownloadManager.
	 */
	@Test
	public void testAddDownload() {
		System.out.println("addDownload");
		String URL = "";
		DownloadManager instance = null;
		try {
			instance.addDownload(URL);
		} catch (MalformedURLException ex) {
			Logger.getLogger(DownloadManagerTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of startDownloading method, of class DownloadManager.
	 */
	@Test
	public void testStartDownloading() {
		System.out.println("startDownloading");
		DownloadManager instance = null;
		instance.startDownloading();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of stopDownloading method, of class DownloadManager.
	 */
	@Test
	public void testStopDownloading() {
		System.out.println("stopDownloading");
		DownloadManager instance = null;
		instance.stopDownloading();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of downloadEventPerformed method, of class DownloadManager.
	 */
	@Test
	public void testDownloadEventPerformed() {
		System.out.println("downloadEventPerformed");
		DownloadEvent downloadEvent = null;
		DownloadManager instance = null;
		instance.downloadEventPerformed(downloadEvent);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
