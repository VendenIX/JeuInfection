package jeu;
import java.util.HashSet;

import algorithms.AlphaBeta;

public class Test {

    public static void main(String[]args){

        State etat = new State();
        AlphaBeta a = new AlphaBeta(4);
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        long startTime = System.nanoTime();
        int i = 10;
        int n = 0;
        
        while (!etat.isOver() && !repetition && (n<i)){
            Move coup = a.getBestMoveArray(etat,etat.getTurn());
            etat = etat.play(coup);
            for(State Presentetat: history){
                if((Presentetat.getTurn() == etat.getTurn()) && (Presentetat.sameBoard(etat.board))){
                    System.out.println(etat.nbPionBleu +","+ etat.nbPionRouge);
                    repetition = true;
                }
            }
            n++;
            history.add(etat);
        }
        etat.printBoard();
        HashSet<Move> LegalMove = etat.getMove();
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/1000000000 + " seconds");
        
    }
    
}
