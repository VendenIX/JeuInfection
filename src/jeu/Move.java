package jeu;
public class Move{
    int[] start;
    int[] end;
    boolean jump;

    public Move(int[] start,int[] end, boolean jump){
        this.start = start;
        this.end = end;
        this.jump = jump;
    }

    public String toString(){
        return "Move from " + this.start[0] + ","+ this.start[1] + " to " + this.end[0]+","+this.end[1] + " by jumping " + this.jump;
    }
}