package tests;


        import main.dataInterfaces.XMLReader;
        import main.dataTransfer.Solve;
        import org.xml.sax.SAXException;

        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.transform.TransformerException;
        import java.io.*;
        import java.util.LinkedList;
        import java.util.List;

public class CSVReader {

    public void CSVReader() {
        //TODO
    }


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {

        XMLReader reader1 = new XMLReader();
        reader1.update();
        reader1.write("4x4x4","Yau-Method","newPath");
    }
}
