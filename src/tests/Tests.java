package tests;


import main.dataTransfer.Solve;

import java.util.LinkedList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        List<Solve> list = new LinkedList<>();
        list.add(new Solve(" "," ","19.00","",""));
        list.add(new Solve(" "," ","20.00","",""));
        list.add(new Solve(" "," ","21.00","",""));
        System.out.println(Double.toString(list.stream().mapToDouble(num -> Double.parseDouble(num.getSolveTime())).average().orElse(0.00)));
    }
}
