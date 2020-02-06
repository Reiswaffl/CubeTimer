package main.dataInterfaces;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLReader {
    private Document doc;
    private String path;

    public XMLReader() {
        doc = null;
        //TODO adjust path
        path = "data/pathData.xml";
    }

    /**
     * @param type type of the Puzzle -> for example 2x2x2 or 4x4x4
     * @param spec specialization of the Puzzle -> for example CFOP or Yau-Method
     * @param path path where the *.csv-File with the times is saves
     * @throws TransformerException
     */
    public void write(String type, String spec, String path) throws TransformerException {
        // get root Node
        Node parent = doc.getElementsByTagName("root").item(0);
        // Create new Element
        Element element = doc.createElement("puzzle");
        element.setAttribute("type", type);
        element.setAttribute("spec",spec);
        element.setTextContent(path);
        parent.appendChild(element);
        // Write into file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(this.path));
        transformer.transform(source, result);

    }

    /**
     * @param type type of the Puzzle
     * @param spec specialization of the Puzzle
     * @return path where the *.csv-File with the times is saved
     */
    public String getPath(String type, String spec){
        // Get Puzzle-Nodes
        NodeList nList = doc.getElementsByTagName("puzzle");
        for(int i = 0; i < nList.getLength(); i++){
            Node n = nList.item(i);
            // Check if ELEMENT_NDOE
            if(n.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) n;
                // Check if its the searched Node
                if(e.getAttribute(type).equals(type) && e.getAttribute(spec).equals(spec))
                    return e.getTextContent();
            }
        }
        // when Node is not there return null
        return null;
    }

    public void update() throws ParserConfigurationException, IOException, SAXException {
        // Reload the file
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        doc = dBuilder.parse(file);
    }

}
