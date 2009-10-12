package jdownloadmon;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.xml.sax.SAXException;

/**
 * Class used for loading and saving xml config files.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class XMLConfigFile extends XMLFile {

	/** Location of the xml file. */
	private static final String LOCATION = "config.xml";
	/** The default directory setting of this config file. */
	private String mDefaultDirectory;
	/** The max # of downloads setting of this config file. */
	private int mMaxDownloads;
	/** The default file exists behavior setting of this config file. */
	private DownloadManager.DefaultFileExistsBehavior mDefaultFileExistsBehavior;

	/**
	 * @return The default directory setting, as a String.
	 */
	public String getDefaultDirectory() {
		return mDefaultDirectory;
	}

	/**
	 * @return The default file exists behavior, as a String.
	 */
	public DownloadManager.DefaultFileExistsBehavior getDefaultFileExistsBehavior() {
		return mDefaultFileExistsBehavior;
	}

	/**
	 * @return The max downloads setting, as an integer.
	 */
	public int getMaxDownloads() {
		return mMaxDownloads;
	}

	/**
	 * Construct a config file.
	 * @param defaultDirectory The setting for the default directory.
	 * @param maxDownloads The setting for max # of downloads.
	 * @param defaultFileExistsBehavior The setting for default file exists behavior.
	 */
	public XMLConfigFile(String defaultDirectory, int maxDownloads, DownloadManager.DefaultFileExistsBehavior defaultFileExistsBehavior) {
		super(LOCATION);
		mDefaultDirectory = defaultDirectory;
		mMaxDownloads = maxDownloads;
		mDefaultFileExistsBehavior = defaultFileExistsBehavior;
	}

	/**
	 * Load the config file.
	 * @return An xml config file if the file exists, <tt>null</tt> otherwise.
	 * @throws ParserConfigurationException If there was an error parsing the file.
	 * @throws SAXException If there was an error parsing the file.
	 * @throws IOException If there was an error parsing the file.
	 * @throws XPathExpressionException If the xml file did not contain the right elements.
	 */
	public static XMLConfigFile loadFile() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
		Document doc = getFile(LOCATION);
		if (doc == null) {
			return null;
		}

		XPath xpath = getXPath();

		Node defaultDirectory = (Node) xpath.compile("/config/defaultDirectory").evaluate(doc, XPathConstants.NODE);
		Node maxDownloads = (Node) xpath.compile("/config/maxDownloads").evaluate(doc, XPathConstants.NODE);
		Node defaultFileExistsBehavior = (Node) xpath.compile("/config/defaultFileExistsBehavior").evaluate(doc, XPathConstants.NODE);

		String directory = defaultDirectory.getTextContent().trim();
		int downloads = Integer.parseInt(maxDownloads.getTextContent().trim());
		DownloadManager.DefaultFileExistsBehavior fileExists = DownloadManager.DefaultFileExistsBehavior.valueOf(
				defaultFileExistsBehavior.getTextContent().trim());

		return new XMLConfigFile(directory, downloads, fileExists);
	}

	/**
	 * Save the config file.
	 * @throws ParserConfigurationException If there was an error parsing.
	 * @throws TransformerConfigurationException If there was an error transforming the file.
	 * @throws TransformerException If there was an error transforming the file
	 */
	public void saveFile()
			throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

		Document doc = getFreshDocument();

		Node configNode = doc.createElement("config");
		doc.appendChild(configNode);

		Node defaultDirectoryNode = doc.createElement("defaultDirectory");
		defaultDirectoryNode.setTextContent(mDefaultDirectory);
		configNode.appendChild(defaultDirectoryNode);

		Node maxDownloadsNode = doc.createElement("maxDownloads");
		maxDownloadsNode.setTextContent(Integer.toString(mMaxDownloads));
		configNode.appendChild(maxDownloadsNode);

		Node defaultFileExistsBehaviorNode = doc.createElement("defaultFileExistsBehavior");
		defaultFileExistsBehaviorNode.setTextContent(mDefaultFileExistsBehavior.toString());
		configNode.appendChild(defaultFileExistsBehaviorNode);

		saveDocument(doc);
	}
}
