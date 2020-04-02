package main.Logic;

import main.dataInterfaces.CSVReader;
import main.dataTransfer.Solve;

public class Logic {

    public Logic(){

    }

    public void save(Solve solve ){
        CSVReader.write();
    }
}
