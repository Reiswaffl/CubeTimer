package main.dataTransfer;


public class Solve {
    private String puzzle;
    private String specializations;
    private String solveTime;
    private String date;
    private String scramble;

    public Solve(String puzzle, String specializations, String solveTime, String date,String scramble){
        this.puzzle = puzzle;
        this.specializations = specializations;
        this.solveTime = solveTime;
        this.date = date;
        this.scramble = scramble;
    }

    public String getDate() {
        return date;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public String getScramble() {
        return scramble;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public String getSpecializations() {
        return specializations;
    }

    @Override
    public String toString(){
        return solveTime +" : "+ date;
    }
}
