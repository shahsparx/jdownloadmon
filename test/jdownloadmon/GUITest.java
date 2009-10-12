/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdownloadmon;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdownloadmon.gui.GUI;

import org.junit.Test;

/**
 *
 * @author UserXP
 */
public class GUITest {

	/**
	 * Test the gui operations on many downloads at once.
	 *
	 * @throws InterruptedException if thread was interrupted.
	 */
	@Test
	public void testGUI() throws InterruptedException {
		try {
			GUI.INSTANCE.init();
			DownloadManager.INSTANCE.init();
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://download.bittorrent.com/dl/BitTorrent-6.2.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://sourceforge.net/projects/pidgin/files/Pidgin/pidgin-2.6.2.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://downloads.sourceforge.net/clamwin/clamwin-0.95.2-setup.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://ftp.bayanat.com.sa/pub/mirror/ftp.openoffice.org/stable/3.1.1/OOo_3.1.1_Win32Intel_install_wJRE_en-US.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://www.abisource.com/downloads/abiword/2.6.8/Windows/abiword-setup-2.6.8.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://mirror.switch.ch/ftp/mirror/mozilla/thunderbird/releases/2.0.0.23/win32/en-US/Thunderbird%20Setup%202.0.0.23.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://mozilla-west.superbhosting.net/firefox/releases/3.5.3/win32/en-US/Firefox%20Setup%203.5.3.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://ftp.free.org/mirrors/videolan/vlc/1.0.2/win32/vlc-1.0.2-win32.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://www.ohloh.net/projects/filezilla/download?filename=FileZilla_3.2.8_win32-setup.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://www.tranglos.com/free/files/kntsetup.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://handbrake.fr/rotation.php?file=HandBrake-0.9.3-Win_GUI.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://daigertech.com/handbrake/HandBrake-0.9.3-Win_GUI.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://www.silverex.org/download/xchat-2.8.6-2.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://sunet.dl.sourceforge.net/project/keepass/KeePass%201.x/1.16/KeePass-1.16-Setup.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://www.truecrypt.org/download/transient/a1724fcc43/TrueCrypt%20Setup%206.2a.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
			GUI.INSTANCE.addDownloadObject(DownloadManager.INSTANCE.addDownload("http://sunet.dl.sourceforge.net/project/pdfcreator/PDFCreator/PDFCreator%200.9.8/PDFCreator-0_9_8_setup.exe", DownloadManager.INSTANCE.getDefaultDirectory()));
		} catch (Exception ex) {
			Logger.getLogger(GUITest.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			Thread.sleep(Long.MAX_VALUE);
		}
	}
}