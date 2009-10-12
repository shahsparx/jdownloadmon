package jdownloadmon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A file to store information about ongoing downloads.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class XMLDownloadsFile extends XMLFile {

	/** Location of the xml file. */
	private static final String LOCATION = "downloads.xml";
	/** List of download objects this file contains. */
	private ArrayList<DownloadObject> mDownloads;

	/**
	 * Construct a downloads XML file.
	 * @param downloads The array list of downloads that this file contains.
	 */
	public XMLDownloadsFile(ArrayList<DownloadObject> downloads) {
		super(LOCATION);
		mDownloads = downloads;
	}

	/**
	 * @return The download objects in this xml file.
	 */
	public ArrayList<DownloadObject> getDownloads() {
		return mDownloads;
	}

	/**
	 * Load the xml downloads file.
	 * @return The loaded file, or <tt>null</tt> if the file couldn't be loaded.
	 * @throws ParserConfigurationException if there was an error configuring the parser.
	 * @throws SAXException if there was an error parsing the file.
	 * @throws IOException if there was an error parsing the file.
	 * @throws XPathExpressionException if the xml file did not contain the right elements.
	 */
	public static XMLDownloadsFile loadFile() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
		ArrayList<DownloadObject> downloadList = new ArrayList<DownloadObject>();

		Document doc = getFile(LOCATION);
		if (doc == null) {
			return null;
		}

		XPath xpath = getXPath();

		NodeList downloads = (NodeList) xpath.compile("/downloads/downloadObject").evaluate(doc, XPathConstants.NODESET);
		for (int i = 1; i <= downloads.getLength(); i++) {
			Node sizeNode = (Node) xpath.compile("/downloads/downloadObject[" + i + "]/size").evaluate(doc, XPathConstants.NODE);
			Node destinationNode = (Node) xpath.compile("/downloads/downloadObject[" + i + "]/destination").evaluate(doc, XPathConstants.NODE);
			Node urlNode = (Node) xpath.compile("/downloads/downloadObject[" + i + "]/url").evaluate(doc, XPathConstants.NODE);

			String path = destinationNode.getTextContent().trim();

			try {
				DownloadObject object = DownloadManager.INSTANCE.createDownloadObjectWithPath(urlNode.getTextContent().trim(), path);
				object.setSize(Long.parseLong(sizeNode.getTextContent().trim()));
				object.setDownloadedSize(DownloadFile.getFileLength(path));

				downloadList.add(object);
			} catch (Exception e) {
				DownloadLogger.LOGGER.severe(e.toString());
			}
		}

		return new XMLDownloadsFile(downloadList);
	}

	/**
	 * Save the config file.
	 * @throws ParserConfigurationException If there was an error parsing.
	 * @throws TransformerConfigurationException If there was an error transforming the file.
	 * @throws TransformerException If there was an error transforming the file.
	 */
	public void saveFile() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Document doc = getFreshDocument();
		Node downloads = doc.createElement("downloads");
		doc.appendChild(downloads);

		for (DownloadObject object : mDownloads) {
			Node download = doc.createElement("downloadObject");

			Node size = doc.createElement("size");
			size.setTextContent("" + object.getSize());
			download.appendChild(size);

			Node destination = doc.createElement("destination");
			destination.setTextContent(object.getDestination());
			download.appendChild(destination);

			Node url = doc.createElement("url");
			url.setTextContent(object.getConnection().getURL().toString());
			download.appendChild(url);

			downloads.appendChild(download);
		}

		saveDocument(doc);
	}
}
