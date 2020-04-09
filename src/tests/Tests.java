package tests;


import main.dataTransfer.Solve;

import java.util.LinkedList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        Solve s = new Solve(",","","1.12.31","","");
       String cur = s.getSolveTime();
        cur = "a.b.c";
       String[] curArr = cur.split("\\.");
        System.out.println(curArr.length);
    }
}
