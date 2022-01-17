package algorithms;
import jeu.Move;
import jeu.State;
public abstract class alphaBeta extends algorithm
{                                           // profondeur 
    public double recherche(State etat, double alpha, double beta, int depth)
    {
        if(depth == 0 || etat.isOver())
        {
            return etat.getScore();
        }
        if(etat.getTurn() == etat.getPlayer())
        {
            for(Move coup : etat.getMove())
            {
                State nextState = etat.play(coup);
                double m = this.recherche(nextState, alpha, beta,depth-1);
                if(alpha < m)
                {
                    alpha = m;
                }
                if(alpha >= beta)
                {
                    return alpha;
                }
            }
            return alpha;
        }
        else
        {
            for(Move coup : etat.getMove())
            {
                State nextState = etat.play(coup);
                double m = this.recherche(nextState, alpha, beta,depth-1);
                if(beta > m)
                {
                    beta = m;
                }
                if(beta <= alpha)
                {
                    return beta;
                }
            }
            return beta;
        }
    }
    
}