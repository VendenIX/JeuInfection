package jeu;
import java.util.HashSet;
import algorithms.*;
public class TestTrie {
    public static void main(String[] args) {
        AlphaBeta a = new AlphaBeta(6);
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        State s = new State();
        s.printBoard();
        long startTime = System.nanoTime();
        
        while (!s.isOver() && !repetition){
            Move coup = a.getBestMoveArray(s,s.getTurn());
            System.out.println("coup choisi"+coup);
            s = s.play(coup);
            System.out.println("Score des "+s.getPlayer()+":  "+s.getScore('b')+"\nTour: "+s.getNbTurn());
            for(State etat: history){
                if((etat.getTurn() == s.getTurn()) && (etat.sameBoard(s.board))){
                    System.out.println(s.nbPionBleu +","+ s.nbPionRouge);
                    repetition = true;
                }
            }
            history.add(s);
        }
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/1000000000 + " seconds");
        } 
        
    }
