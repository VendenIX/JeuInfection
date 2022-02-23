package jeu;
public class Move{
    private int[] start;
    private int[] end;
    private boolean jump;

    public Move(int[] start,int[] end, boolean jump){
        this.start = start;
        this.end = end;
        this.jump = jump;
    }
    /**
     * Crée une instance Move 
     * @param i coor x case de départ
     * @param j coor y case de départ
     * @param k coor x case d'arrivée
     * @param l coor y case d'arrivée
     * @require i >= 0 && i <= 6
     * @require j >= 0 && j <= 6
     * @require k >= 0 && k <= 6
     * @require l >= 0 && l <= 6
     * @param jump booléen indiquant si le coup est saut
     */
    public Move(int i,int j,int k,int l, boolean jump){
        this.start = new int[2];
        this.end = new int[2];
        this.start[0] = i;
        this.start[1] = j;
        this.end[0] = k;
        this.end[1] = l;
        this.jump = jump;
    }

    public int[] getStart() {
        return this.start;
    }

    public int[] getEnd() {
        return this.end;
    }

    public boolean getJump() {
        return this.jump;
    }
    /**
     * Fonction qui détermine si deux move de duplication amène au même plateau
     * @param coup coup à comparer
     * @require coup != null
     * @return true si les coups ont la même finalité, false si les coups sont différents
     */
    public boolean isSameDuplication(Move coup){
        if ((this.end[0]==coup.end[0])&&(this.end[1]==this.end[1])&&(!this.jump)&&(!coup.jump)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Move from " + this.start[0] + ","+ this.start[1] + " to " + this.end[0]+","+this.end[1] + " by jumping " + this.jump;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Move)) {
            return false;
        }
        Move move = (Move) o;
        return (this.start[0] == move.getStart()[0]) && (this.start[1] == move.getStart()[1]) && (this.end[0] == move.getEnd()[0])&& (end[1] == move.getEnd()[1]) && jump == move.getJump();
    }
}