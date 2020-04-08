package tests;


import main.dataTransfer.Solve;

import java.util.LinkedList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        String s = "1:20:34";
        String[] arr = s.split(":");
        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }
}
