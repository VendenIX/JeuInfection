package algorithms;
import jeu.Move;
import jeu.State;
public class MinMax extends Algorithm
{   
    public MinMax(int depth)
    {
        super(depth);
    }
    public double recherche(State etat,char player){
        return this.recherche(etat, player,this.depth);
    }
    public double recherche(State etat, char player, int depth)
    {
        if(depth == 0 || etat.isOver())
        {
            return etat.getScore(player);
        }
        double b;
        if(etat.getTurn() == player)
        {
            b = Double.NEGATIVE_INFINITY;
            for(Move coup : etat.getMove())
            {
                super.visitedNodes++;
                State nextEtat = etat.play(coup);
                double m = this.recherche(nextEtat, player ,depth-1);
                if(b < m)
                {
                    b = m;
                }
            }
        }
        else
        {
            b = Double.POSITIVE_INFINITY;
            for(Move coup : etat.getMove())
            {
                super.visitedNodes++;
                State nextEtat = etat.play(coup);
                double m = this.recherche(nextEtat, player ,depth-1);
                if(b > m)
                {
                    b = m;
                }
            }

        }
        return b;
    }
}