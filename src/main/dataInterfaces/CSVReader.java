package main.dataInterfaces;


import main.dataTransfer.Solve;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

    public void CSVReader() {
        //TODO
    }

    /**
     * @param input solve-data converted to String
     * @param path  path where the file is found
     * @throws IOException
     */
    public void write(String input, String path) throws IOException {

        FileWriter writer = new FileWriter(new File(path), true);
        writer.append(input).append("\n");
        writer.flush();
        writer.close();
    }

   /**
     * @param path where the file is found
     * @return List of all solve-data in the direction the path leads to
     * @throws IOException
     */
    private List<String> read(String path) throws IOException {
        List<String> ret = new LinkedList<>();
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            ret.add(line);
        }
        return ret;
    }

    /**
     * @param path where the file is found
     * @return list of solve-data converted to "Solve-Object"
     * @throws IOException
     */
    public List<Solve> readSolveForm(String path) throws IOException {
        List<Solve> ret = new LinkedList<>();
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(",");
            ret.add(new Solve(arr[0], arr[1].replace("\"", ""), arr[2], arr[3], arr[arr.length - 1].replace("\"", "")));
        }
        return ret;
    }


}
