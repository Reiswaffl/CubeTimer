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
                ret.add(new Solve(arr[0],arr[1].replace("\"",""),arr[2],arr[3],arr[arr.length-1].replace("\"","")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static void main(String[] args) {
        String csvFile = "data/export (1).csv";
        CSVReader reader = new CSVReader();
        List<Solve> list = reader.readSolveForm(csvFile);
        System.out.println(list.get(0).getPuzzle());
        System.out.println(list.get(0).getSpecializations());
        System.out.println(list.get(0).getSolveTime());
        System.out.println(list.get(0).getDate());
        System.out.println(list.get(0).getScramble());
        System.out.println(list.get(0).toString());
    }

}
