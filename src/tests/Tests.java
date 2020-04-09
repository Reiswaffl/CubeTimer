package tests;



import main.dataInterfaces.CSVReader;
import main.dataTransfer.Solve;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        Solve s = new Solve("","","","Apr 09 2020 - 12:09:26","");
        try {
            CSVReader.delete(s,"data/testData1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
