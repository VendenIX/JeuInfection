package algorithms;
import jeu.Move;
import jeu.State;
public class AlphaBeta extends Algorithm
{                                          
    public AlphaBeta(int depth)
    {
        super(depth);
    }

    public double recherche(State etat, char player)
    {
        return recherche(etat,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY, player, this.depth);
    }
    public double recherche(State etat, double alpha, double beta, char player, int depth)
    {
        if(depth == 0 || etat.isOver())
        {
            return etat.getScore(player);
        }
        if(etat.getTurn() == player)
        {
            for(Move coup : etat.getMove())
            {
                State nextState = etat.play(coup);
                double m = this.recherche(nextState, alpha, beta, player,depth-1);
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
                double m = this.recherche(nextState, alpha, beta, player,depth-1);
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