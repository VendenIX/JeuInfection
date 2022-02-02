package jeu;
import java.util.ArrayList;
import java.util.HashSet;

import algorithms.AlphaBeta;

public class Test {

    public static void main(String[]args){

        State etat = new State();
        AlphaBeta a = new AlphaBeta(4);
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        long startTime = System.nanoTime();
        int i = 20;
        int n = 0;
        
        while (!etat.isOver() && !repetition && (n<i)){
            Move coup = a.getBestMove(etat,etat.getTurn());
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
        System.out.println(a.getVisitedNodes());
        HashSet<Move> LegalMove = etat.getMove();
        ArrayList<Move> LegalMoveTrie = etat.triInfection(LegalMove, etat.getTurn());
        System.out.println(LegalMoveTrie.containsAll(LegalMove));
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/1000000000 + " seconds");
        
    }
    
}
