package algorithms;
import jeu.Move;
import jeu.State;
public abstract class minMax extends algorithm
{                                           // profondeur 
    public Double recherche(State etat, int depth)
    {
        if(depth == 0 || etat.isOver())
        {
            return etat.getScore();
        }
        double b;
        if(etat.getTurn() == etat.getPlayer())
        {
            b = Double.NEGATIVE_INFINITY;
            for(Move coup : etat.getMove())
            {
                State nextEtat = etat.play(coup);
                double m = this.recherche(nextEtat, depth-1);
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
                State nextEtat = etat.play(coup);
                double m = this.recherche(nextEtat, depth-1);
                if(b > m)
                {
                    b = m;
                }
            }

        }
        return b;
    }
}