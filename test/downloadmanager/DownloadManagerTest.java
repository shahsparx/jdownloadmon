package downloadmanager;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for DownloadManager class.
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
	DownloadObject downloadObject = new DownloadObject(null, null);
	DownloadManager instance = new DownloadManager();
	boolean expResult = true;
	boolean result;
	for (int i = 0; i < 3; i++) {
	    result = instance.addToActiveList(downloadObject);
	    assertEquals(expResult, result);
	}
	
	expResult = false;
	result = instance.addToActiveList(downloadObject);
	assertEquals(expResult, result);
    }

    /**
     * Test of getSelectedList method, of class DownloadManager.
     */
    @Test
    public void testGetSelectedList() {
	System.out.println("getSelectedList");
	DownloadManager instance = new DownloadManager();
	ArrayList expResult = null;
	ArrayList result = instance.getSelectedList();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of addDownload method, of class DownloadManager.
     */
    @Test
    public void testAddDownload() {
	System.out.println("addDownload");
	String URL = "";
	DownloadManager instance = new DownloadManager();
	instance.addDownload(URL);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of startDownloading method, of class DownloadManager.
     */
    @Test
    public void testStartDownloading() {
	System.out.println("startDownloading");
	DownloadManager instance = new DownloadManager();
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
	DownloadManager instance = new DownloadManager();
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
	DownloadManager instance = new DownloadManager();
	instance.downloadEventPerformed(downloadEvent);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
}
