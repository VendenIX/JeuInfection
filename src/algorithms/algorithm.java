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
public abstract class algorithm
{                                         
    public Move getBestMove(State etat, int depth)
    {
        Move bestAction = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        for(Move coup : etat.getMove())
        {
            State nextState = etat.play(coup);
            Double value = recherche(nextState, depth);
            if(value > bestValue)
            {
                bestValue = value;
                bestAction = coup;
            }
        }
        return bestAction;
    }

    public abstract Double recherche(State etat, int entier);



}