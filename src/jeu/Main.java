package jeu;
import java.util.HashSet;
import algorithms.*;
public class Main {

    public static void main(String[] args){
        AlphaBeta a = new AlphaBeta(4);
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        State s = new State();
        s.printLegalMove(s.getMove());
        s.printBoard();
        long startTime = System.nanoTime();
        
        while (!s.isOver() && !repetition){
            Move coup = a.getBestMove(s,s.getTurn());
            s = s.play(coup);
            System.out.println(coup);
            s.printBoard();
            System.out.println("Score des "+s.getPlayer()+":  "+s.getScore('b')+"\nTour: "+s.getNbTurn());

            for(State etat: history){
                if((etat.sameBoard(s.board))&&(etat.getTurn()==s.getTurn())){
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