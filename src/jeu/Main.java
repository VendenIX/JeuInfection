package jeu;
import java.util.HashSet;
public class Main {

    public static void main(String[] args){

        HashSet<State> history = new HashSet<State>();
        State s = new State();
        s.printLegalMove(s.getMove());
        s.printBoard();
        while (!s.isOver()){
            Move coup = s.getRandomMove();
            s = s.play(coup);
            s.printBoard();
            for(State etat: history){
                if((etat.getTurn() == s.getTurn()) && (etat.board == s.board)){
                    break;
                }
            }
            history.add(s);
        }
        } 
    }