package downloadmanager;

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
public class DownloadObjectTest {

	public DownloadObjectTest() {
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
	 * Test of run method, of class DownloadObject.
	 */
	@Test
	public void testRun() {
		System.out.println("run");
		DownloadObject instance = null;
		instance.run();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addListener method, of class DownloadObject.
	 */
	@Test
	public void testAddListener() {
		System.out.println("addListener");
		DownloadObserver observer = null;
		DownloadObject instance = null;
		instance.addListener(observer);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeListener method, of class DownloadObject.
	 */
	@Test
	public void testRemoveListener() {
		System.out.println("removeListener");
		DownloadObserver observer = null;
		DownloadObject instance = null;
		instance.removeListener(observer);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of notifyListeners method, of class DownloadObject.
	 */
	@Test
	public void testNotifyListeners() {
		System.out.println("notifyListeners");
		DownloadEvent downloadEvent = null;
		DownloadObject instance = null;
		instance.notifyListeners(downloadEvent);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStatusState method, of class DownloadObject.
	 */
	@Test
	public void testGetStatusState() {
		System.out.println("getStatusState");
		DownloadObject instance = null;
		StatusState expResult = null;
		StatusState result = instance.getStatusState();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPercentDownloaded method, of class DownloadObject.
	 */
	@Test
	public void testGetPercentDownloaded() {
		System.out.println("getPercentDownloaded");
		DownloadObject instance = null;
		int expResult = 0;
		int result = instance.getPercentDownloaded();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setStatusState method, of class DownloadObject.
	 */
	@Test
	public void testSetState() {
		System.out.println("setState");
		StatusState state = null;
		DownloadObject instance = null;
		instance.setStatusState(state);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDestination method, of class DownloadObject.
	 */
	@Test
	public void testGetDestination() {
		System.out.println("getDestination");
		DownloadObject instance = null;
		String expResult = "";
		String result = instance.getDestination();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setDestination method, of class DownloadObject.
	 */
	@Test
	public void testSetDestination() throws Exception {
		System.out.println("setDestination");
		String newDestination = "";
		DownloadObject instance = null;
		instance.setDestination(newDestination);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getConnection method, of class DownloadObject.
	 */
	@Test
	public void testGetConnection() {
		System.out.println("getConnection");
		DownloadObject instance = null;
		DownloadConnection expResult = null;
		DownloadConnection result = instance.getConnection();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setConnection method, of class DownloadObject.
	 */
	@Test
	public void testSetConnection() throws Exception {
		System.out.println("setConnection");
		DownloadConnection downloadConnection = null;
		DownloadObject instance = null;
		instance.setConnection(downloadConnection);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of download method, of class DownloadObject.
	 */
	@Test
	public void testDownload() {
		System.out.println("download");
		DownloadObject instance = null;
		instance.download();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
