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
import java.util.LinkedList;
import java.util.List;

public class XMLReader {
    private Document doc;
    private String path;

    public XMLReader() {
        doc = null;
        //TODO adjust path
        path = "data/pathData.xml";
    }

    public List<String> getPuzzles() {
        List<String> ret = new LinkedList<>();
        NodeList nList = getNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nList.item(i);
                ret.add(element.getAttribute("id"));
            }
        }
        return ret;
    }

    public List<String> getSpecializations(String puzzle) {
        List<String> ret = new LinkedList<>();
        NodeList nList = getNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nList.item(i);
                // find puzzle
                if (element.getAttribute("id").equals(puzzle)) {
                    NodeList specs = nList.item(i).getChildNodes();
                    // list specs
                    for (int j = 0; j < specs.getLength(); j++) {
                        if (specs.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element specElement = (Element) specs.item(j);
                            if (specElement != null)
                                ret.add(specElement.getAttribute("id"));
                        }
                    }
                    return ret;
                }
            }
        }
        return ret;
    }

    public String getPath(String puzzle, String spec) {
        NodeList nList = getNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nList.item(i);
                // find puzzle
                if (element.getAttribute("id").equals(puzzle)) {
                    NodeList specs = nList.item(i).getChildNodes();
                    // find specs
                    for (int j = 0; j < specs.getLength(); j++) {
                        if (specs.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element specElement = (Element) specs.item(j);
                            // return path
                            if (specElement != null)
                                if (specElement.getAttribute("id").equals(spec))
                                    return specElement.getTextContent();

                        }
                    }
                }
            }
        }
        return null;
    }

    public void writePuzzle(String puzzle) throws TransformerException {
        NodeList nList = doc.getElementsByTagName("root");
        for (int i = 0; i < nList.getLength(); i++)
            if (nList.item(i).getNodeType() == Node.ELEMENT_NODE)
                write(nList.item(i), "puzzle", puzzle);
    }

    public void writeSpec(String puzzle, String spec) throws TransformerException {
        NodeList nList = doc.getElementsByTagName("puzzle");
        for(int i = 0; i < nList.getLength(); i++)
            if(nList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nList.item(i);
                if(element.getAttribute("id").equals(puzzle)) {
                    write(nList.item(i), "specializations", spec);
                    break;
                }
            }

    }

    public void writePath(String puzzle, String spec, String path) throws TransformerException {
        NodeList nList = doc.getElementsByTagName("puzzle");
        loop : for(int i = 0; i < nList.getLength(); i++)
            if(nList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nList.item(i);
                if(element.getAttribute("id").equals(puzzle)) {
                    NodeList specs = nList.item(i).getChildNodes();
                    for(int j = 0; j < specs.getLength(); j++){
                        if(specs.item(j).getNodeType() == Node.ELEMENT_NODE){
                            Element specElement = (Element) specs.item(j);
                            if(specElement.getAttribute("id").equals(spec)) {
                                writeText(specs.item(j), spec, path);
                                break loop;
                            }
                        }
                    }
                }
            }
    }

    private void write(Node parent, String tag, String id) throws TransformerException {
        Element element = doc.createElement(tag);
        element.setAttribute("id", id);
        parent.appendChild(element);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }

    private void writeText(Node parent, String tag, String content) throws TransformerException {
        Element element = (Element) parent;
        element.setTextContent(content);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }
    public void update() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        doc = dBuilder.parse(file);
    }

    private NodeList getNodes() {
        return doc.getElementsByTagName("puzzle");
    }


}
