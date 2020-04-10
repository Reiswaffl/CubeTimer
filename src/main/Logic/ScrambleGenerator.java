package main.Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ScrambleGenerator {
    /**
     *
     * @param p puzzle, fuer das der Scramble generiert werden soll
     * @return requested scramble
     */
    public static String generate(Puzzles p){
        switch (p){
            case TWO:
                return generate2();
            case THREE:
                return generate3();
             case FOUR:
                 return generate4();
            case FIVE:
                return generate5();
            case SIX:
                return generate6();
            case SEVEN:
                return generate7();
            case PYRAMINX:
                return generateP();
            case SKEWB:
                return generateS();
            default:
                return "Fuck U";



        }
    }
    private static String generate2(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        int size = 7 + r.nextInt(5);
        for(int i = 0; i < size; i++){
            String cur = turns[r.nextInt(6)];
            while (cur.equals(last))
                cur = turns[r.nextInt(6)];
            last = cur;
            ret.append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
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

    public static String generate4(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B","d","r","l","u","f","b"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        for(int i = 0; i < 41; i++){
            String cur = turns[r.nextInt(12)];
            while (cur.equals(last))
                cur = turns[r.nextInt(12)];
            last = cur;
            ret.append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }

    public static String generate5(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B","d","r","l","u","f","b"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        for(int i = 0; i < 41; i++){
            String cur = turns[r.nextInt(12)];
            while (cur.equals(last))
                cur = turns[r.nextInt(12)];
            last = cur;
            ret.append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }

    public static String generate6(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B"};
        String[] spec = {"2","'"," "};
        String[] prefix = {"","2","3"};
        String last = "";
        Random r = new Random();
        for(int i = 0; i < 81; i++){
            String cur = turns[r.nextInt(6)];
            while (cur.equals(last))
                cur = turns[r.nextInt(6)];
            last = cur;
            ret.append(prefix[r.nextInt(3)]).append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }

    public static String generate7(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B"};
        String[] spec = {"2","'"," "};
        String[] prefix = {"","2","3"};
        String last = "";
        Random r = new Random();
        for(int i = 0; i < 101; i++){
            String cur = turns[r.nextInt(6)];
            while (cur.equals(last))
                cur = turns[r.nextInt(6)];
            last = cur;
            ret.append(prefix[r.nextInt(3)]).append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }

    public static String generateP(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B","d","r","l","u","f","b"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        int length = 13 + r.nextInt(3);
        for(int i = 0; i < length; i++){
            String cur = turns[r.nextInt(12)];
            while (cur.equals(last))
                cur = turns[r.nextInt(12)];
            last = cur;
            ret.append(cur).append(spec[r.nextInt(3)]).append(" ");
        }
        return ret.toString();
    }

    private static String generateS(){
        StringBuilder ret = new StringBuilder();
        String[] turns = {"R","L","U","D","F","B"};
        String[] spec = {"2","'"," "};
        String last = "";
        Random r = new Random();
        int size = 13 + r.nextInt(5);
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
