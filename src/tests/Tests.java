package tests;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Tests {
    public static void main(String[] args) {
        String format = "MMM dd,yyyy - HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();
        System.out.println(formatter.format(date));
    }
}
