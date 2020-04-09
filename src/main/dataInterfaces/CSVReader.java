package main.dataInterfaces;


import main.dataTransfer.Solve;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {



    /**
     * @param input solve-data converted to String
     * @param path  path where the file is found
     * @throws IOException
     */
    public static void write(String input, String path) throws IOException {
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
    private static List<String> read(String path) throws IOException {
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
    public static List<Solve> readSolveForm(String path) throws IOException {
        List<Solve> ret = new LinkedList<>();
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(",");
            ret.add(new Solve(arr[0], arr[1].replace("\"", ""), arr[2], arr[3], arr[arr.length - 1].replace("\"", "")));
        }
        return ret;
    }

    public static void delete(Solve solve , String path) throws IOException {
        List<String> list = read(path);
        for(String s : list){
            System.out.println(s);
            String[] cur = s.replace("\"","").split(",");
            if(cur[3].equals(solve.getDate())){
                list.remove(s);
                System.out.println("Hello");
                break;
            }
        }
        String ret = "";
        StringBuilder builder = new StringBuilder(ret);

        for(String s : list){
            builder.append(s).append("\n");
        }
        FileWriter writer = new FileWriter(new File(path), false);
        writer.write("" + builder);
        writer.flush();
        writer.close();
    }

}
