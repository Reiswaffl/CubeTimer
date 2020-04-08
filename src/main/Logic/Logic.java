package main.Logic;

import main.dataInterfaces.CSVReader;
import main.dataInterfaces.XMLReader;
import main.dataTransfer.Solve;
import org.xml.sax.SAXException;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Logic {


    public static void save(Solve solve ){
        XMLReader reader = new XMLReader();
        try {
            reader.update();
        String path = reader.getPath(solve.getPuzzle(), solve.getSpecializations());
        String csvData = solve.getPuzzle() +","+ solve.getSpecializations() + ","+solve.getSolveTime()+","+solve.getDate() +",,n,n,,"+"\""+solve.getScramble()+"\",";

            CSVReader.write(csvData,path);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static String avg(String puzzle, String spec, int of){
        try{
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle,spec);
            List<Solve> list = CSVReader.readSolveForm(path);
            for(Solve s : list)
                               s.setSolveTime(s.getSolveTime().replace(":","."));
            if(list.size()>= of)
               list =  list.subList(0, of);
            System.out.println("HI");
            String s =Double.toString(Math.round(list.stream().mapToDouble(num -> Double.parseDouble(num.getSolveTime())).average().orElse(0.00)*100.0)/100.0);
            System.out.println(s);
            return s;

        }catch (Exception e){
            //TODO
        }
        return "0.00";
    }
}
