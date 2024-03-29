package algorithms;
import jeu.Move;
import jeu.State;
/*
    Pour faire l'infini en java

        double posInf = Double.POSITIVE_INFINITY;
        double negInf = Double.NEGATIVE_INFINITY;
        depth = profondeur
        m = resultat de la recherche (à 1 profondeur de plus)
 */
public abstract class Algorithm
{                                         
    protected int depth;
    protected int visitedNodes;

    public Algorithm(int depth)
    {
        this.depth=depth;
        this.visitedNodes = 0;
    }

    public int getDepth()
    {
        return depth;
    }

    public int getVisitedNodes(){
        return this.visitedNodes;
    }

    public void setDepth(int depth)
    {
        this.depth=depth;
    }

    public Move getBestMove(State etat, char player)
    {
        Move bestAction = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        for(Move coup : etat.getMove())
        {
            State nextState = etat.play(coup);
            double value = this.recherche(nextState, player);
            if(value > bestValue)
            {
                bestValue = value;
                bestAction = coup;
            }
        }
        return bestAction;
    }

    public abstract double recherche(State etat, char player);
}