package main.Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ScrambleGenerator {
    public static String generate(Puzzles p){
        switch (p){
            case THREE:
                return generate3();
        }
        return null;
    }

    private static String generate3(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        int size = 20 + r.nextInt(5);
        for(int i = 0; i < size; i++){
            String cur = turns[r.nextInt(6)];
            while (cur.equals(last))
                cur = turns[r.nextInt(6)];
            last = cur;
            ret.append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }
}
