package jeu;
import java.util.HashSet;
import algorithms.*;
public class Main {

    public static void main(String[] args){
        MinMax a = new MinMax(2);
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        State s = new State();
        s.printLegalMove(s.getMove());
        s.printBoard();
        while (!s.isOver() && !repetition){
            Move coup = a.getBestMove(s,s.getTurn(), 3);
            s = s.play(coup);
            System.out.println(coup);
            s.printBoard();
            System.out.println("score de "+s.getPlayer()+"  "+s.getScore('b'));
            for(State etat: history){
                if((etat.sameBoard(s.board))&&(etat.getTurn()==s.getTurn())){
                    etat.printBoard();
                    System.out.println("Is same ");
                    s.printBoard();
                    System.out.println(s.nbPionBleu +","+ s.nbPionRouge);
                    repetition = true;
                }
            }
            history.add(s);
        }
        } 
        
    }