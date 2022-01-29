package algorithms;
import java.util.ArrayList;
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

    public double rechercheArray(State etat, char player)
    {
        return rechercheArray(etat,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY, player, this.depth);
    }

    public double rechercheArray(State etat, double alpha, double beta, char player, int depth)
    {
        if(depth == 0 || etat.isOver())
        {
            return etat.getScore(player);
        }
        if(etat.getTurn() == player)
        {
            ArrayList<Move> coup = etat.getArrayMove();
            for(int i = 0;i<coup.size();i++)
            {
                State nextState = etat.play(coup.get(i));
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
            ArrayList<Move> coup = etat.getArrayMove();
            for(int i = 0;i<coup.size();i++)
            {
                State nextState = etat.play(coup.get(i));
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

    public Move getBestMoveArray(State etat, char player)
    {
        Move bestAction = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        ArrayList<Move> coup = etat.getArrayMove(); 
        for(int i = 0;i<coup.size();i++)
        {
            State nextState = etat.play(coup.get(i));
            double value = this.rechercheArray(nextState, player);
            if(value > bestValue)
            {
                bestValue = value;
                bestAction = coup.get(i);
            }
        }
        return bestAction;
    }
    
}