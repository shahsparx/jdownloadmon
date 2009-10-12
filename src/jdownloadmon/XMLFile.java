package jdownloadmon;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Abstract superclass for xml file classes.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public abstract class XMLFile {

	/** Location of the file. */
	protected String mLocation;

	/**
	 * Construct an XML file.
	 * @param location The location of the file on the drive.
	 */
	protected XMLFile(String location) {
		mLocation = location;
	}

	/**
	 * Get a fresh document.
	 * @return The created document.
	 * @throws ParserConfigurationException if there was an error configuring the parser.
	 */
	protected Document getFreshDocument() throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		return docBuilder.newDocument();
	}

	/**
	 * Get a new xpath.
	 * @return a newly created xpath.
	 */
	protected static XPath getXPath() {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		return xPathFactory.newXPath();
	}

	/**
	 * Get a document from a file.
	 * @param location The location of the file.
	 * @return The file, or null if the file couldn't be loaded
	 * @throws ParserConfigurationException if there was an error configuring the parser.
	 * @throws SAXException if there was an error parsing the file.
	 * @throws IOException if there was an error parsing the file.
	 */
	protected static Document getFile(String location) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(location);
		if (!file.exists()) {
			return null;
		}

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		return builder.parse(file);
	}

	/**
	 * Save a document to a file.
	 * @param document The document to be saved.
	 * @throws TransformerException If there was an error transforming the file.
	 */
	protected void saveDocument(Document document) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		StreamResult result = new StreamResult(new File(mLocation));
		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);
	}
}
