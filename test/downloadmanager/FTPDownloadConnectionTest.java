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
public class FTPDownloadConnectionTest {

	public FTPDownloadConnectionTest() {
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
	 * Test of getBytes method, of class FTPDownloadConnection.
	 */
	@Test
	public void testGetBytes() {
		System.out.println("getBytes");
		int position = 0;
		int size = 0;
		FTPDownloadConnection instance = null;
		int[] expResult = null;
		int[] result = instance.getBytes(position, size);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of connect method, of class FTPDownloadConnection.
	 */
	@Test
	public void testConnect() throws Exception {
		System.out.println("connect");
		FTPDownloadConnection instance = null;
		instance.connect();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
