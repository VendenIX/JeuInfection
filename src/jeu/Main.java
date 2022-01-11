package jeu;
import java.util.HashSet;
public class Main {

    public static void main(String[] args){

        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        State s = new State();
        s.printLegalMove(s.getMove());
        s.printBoard();
        while (!s.isOver() && !repetition){
            Move coup = s.getRandomMove();
            s = s.play(coup);
            System.out.println(coup);
            s.printBoard();
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