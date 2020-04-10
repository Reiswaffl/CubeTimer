package tests;



import main.dataInterfaces.CSVReader;
import main.dataInterfaces.XMLReader;
import main.dataTransfer.Solve;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        XMLReader reader = new XMLReader();
        try {
            reader.update();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        reader.getPath("a","b");
    }
}
