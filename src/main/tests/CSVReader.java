package main.tests;


import java.io.*;

public class CSVReader {
    private BufferedReader reader;
    private String path;
    private PrintWriter writer;

    public void CSVReader() {
        //TODO
    }

    public void write(String input, String path) {
        try {
            writer = new PrintWriter(new File(path));
            writer.write(input+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String csvFile = "data/testData.csv";
        CSVReader reader = new CSVReader();
        reader.write("Hello",csvFile);
        reader.write("Hello",csvFile);
        reader.write("Hello",csvFile);
    }

}
