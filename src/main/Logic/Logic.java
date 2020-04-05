package main.Logic;

import main.dataInterfaces.CSVReader;
import main.dataInterfaces.XMLReader;
import main.dataTransfer.Solve;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Logic {


    public static void save(Solve solve ){
        XMLReader reader = new XMLReader();
        try {
            reader.update();
        String path = reader.getPath(solve.getPuzzle(), solve.getSpecializations());
        String csvData = solve.getPuzzle() +","+ solve.getSpecializations() + ","+solve.getSolveTime()+","+solve.getDate() +",,n,n,,"+"\""+solve.getScramble()+"\",";

            CSVReader.write(csvData,path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
