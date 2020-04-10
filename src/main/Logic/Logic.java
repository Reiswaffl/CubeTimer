package main.Logic;

import main.dataInterfaces.CSVReader;
import main.dataInterfaces.XMLReader;
import main.dataTransfer.Solve;
import org.xml.sax.SAXException;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Logic {

    /**
     * Saves a solve which is given from the GUI
     *
     * @param solve a Solve-object with the needed information
     */
    public static void save(Solve solve) {
        XMLReader reader = new XMLReader();
        try {
            // load the right path to csv-file with the solves
            reader.update();
            String path = reader.getPath(solve.getPuzzle(), solve.getSpecializations());
            // get the information into csv-format
            String csvData = solve.getPuzzle() + "," + solve.getSpecializations() + "," + solve.getSolveTime() + "," + solve.getDate() + ",,n,n,," + "\"" + solve.getScramble() + "\",";
            // write into file
            CSVReader.write(csvData, path);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the average a number of solves
     *
     * @param puzzle kind of the puzzle e.g. 3x3x3
     * @param spec   spec of the puzzle e.g. Standard or CFOP
     * @param of     the number of solves the average is supposed to be about
     * @return avg of the given range
     */
    public static String avg(String puzzle, String spec, int of) {
        try {
            // load the right path to csv-file with the solves
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle, spec);
            // get a list of solves
            List<Solve> list = CSVReader.readSolveForm(path);
            for (Solve s : list) {
                // get the Times into the right format and make them able to be seen as double
                String cur = s.getSolveTime().replace(":", ".");
                String[] curArr = cur.split("\\.");
                if (curArr.length > 2)
                    cur = (Integer.toString((Integer.parseInt(curArr[0]) * 60 + Integer.parseInt(curArr[1]))) + "." + curArr[2]);
                s.setSolveTime(cur);
            }
            // make the list shorter if necessary
            if (list.size() >= of)
                list = list.subList(list.size() - of, list.size());
            else
                return "-";
            // calculate average
            double result = list.stream().mapToDouble(num -> Double.parseDouble(num.getSolveTime())).average().orElse(0.00);
            // get it into a nice String-form with (for the GUI)
            if (result >= 60.0)
                return ((int) result / 60) + ":" + ((int) result % 60) + "." + (Math.round((int) (result * 100.0) % 100.0));
            else
                return ((int) result % 60) + "." + (Math.round((int) (result * 100.0) % 100.0));
        } catch (Exception e) {
            //TODO
        }
        // when the program fucked up
        return "-";
    }

    /**
     * @param puzzle puzzle the list is supposed to be about
     * @param spec   spec of the puzzle the list is supposed to be about
     * @param size   size of the list, that will be returned
     * @return list with String-values of the solving-times
     */
    public static List<Solve> listTimes(String puzzle, String spec, int size) {
        // load the right path to csv-file with the solves
        try {
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle, spec);
            // get a list of solves
            List<Solve> list = CSVReader.readSolveForm(path);
            if(list.size() > size) {
                list = list.subList(list.size() - size, list.size());
            }
            return list;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(String puzzle, String spec, Solve solve) {
        try {
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle, spec);
            CSVReader.delete(solve,path);
        } catch (Exception e) {
        }
    }
    public static String lifeTimeBest(String puzzle, String spec){
        try {
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle, spec);
            // get a list of solves
            List<Solve> list = CSVReader.readSolveForm(path);
            for (Solve s : list) {
                    // get the Times into the right format and make them able to be seen as double
                    String cur = s.getSolveTime().replace(":", ".");
                    String[] curArr = cur.split("\\.");
                    if (curArr.length > 2)
                        cur = (Integer.toString((Integer.parseInt(curArr[0]) * 60 + Integer.parseInt(curArr[1]))) + "." + curArr[2]);
                    s.setSolveTime(cur);
            }

            double result = list.stream().mapToDouble(num -> Double.parseDouble(num.getSolveTime())).min().orElse(0.00);
            // get it into a nice String-form with (for the GUI)
            if (result >= 60.0)
                return ((int) result / 60) + ":" + ((int) result % 60) + "." + (Math.round((int) (result * 100.0) % 100.0));
            else
                return ((int) result % 60) + "." + (Math.round((int) (result * 100.0) % 100.0));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return "0.00";
    }
    public static String solvesCount(String puzzle, String spec){
        try {
            XMLReader reader = new XMLReader();
            reader.update();
            String path = reader.getPath(puzzle, spec);
            // get a list of solves
            List<Solve> list = CSVReader.readSolveForm(path);
            return Integer.toString(list.size());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
