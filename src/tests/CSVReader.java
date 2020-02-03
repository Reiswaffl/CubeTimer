package tests;


import main.dataInterfaces.XMLReader;
import main.dataTransfer.Solve;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

    public void CSVReader() {
        //TODO
    }


    public static void main(String[] args) {
        main.dataInterfaces.CSVReader reader = new main.dataInterfaces.CSVReader();
        XMLReader reader1 = new XMLReader();
        try {
            reader1.update();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        List<Solve> solves = reader.readSolveForm(reader1.getPath("3x3x3","CFOP"));
        for(Solve solve : solves)
            System.out.println(solve.toString());
    }

}
