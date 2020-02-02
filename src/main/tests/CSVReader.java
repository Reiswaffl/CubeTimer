package main.tests;


import main.dataTransfer.Solve;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

    public void CSVReader() {
        //TODO
    }

    public void write(String input, String path) {
        try {
            FileWriter writer = new FileWriter(new File(path), true);
            writer.append(input).append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> read(String path){
        List<String> ret = new LinkedList<>();
        String line = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null){
                ret.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Solve> readSolveForm(String path){
        List<Solve> ret = new LinkedList<>();
        String line = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null){
                String[] arr = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static void main(String[] args) {
        String csvFile = "data/testData.csv";
        CSVReader reader = new CSVReader();
        List<String> list = reader.read(csvFile);
        for(String s : list)
            System.out.println(s);
    }

}
