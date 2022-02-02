package jeu;
import java.util.HashSet;
import algorithms.*;
public class Main {
    /**
     * Méthode principale qui joue une partie jusqu'à la fin
     * @param args arguments où arg[0] = profondeur bleu, arg[1] = profondeur rouge et arg[2] = true si on veut utiliser alphabeta, false sinon
     */

    public static void main(String[] args){
        if(args.length!=3){
            throw new IllegalArgumentException("Il faut 3 arguments pour lancer la méthode main");
        }
        Algorithm aBleu,aRouge;
        if(args[2]=="true"){
            aBleu = new AlphaBeta(Integer.parseInt(args[0]));
            aRouge = new AlphaBeta(Integer.parseInt(args[1]));
        }
        else{
            aBleu = new MinMax(Integer.parseInt(args[0]));
            aRouge = new MinMax(Integer.parseInt(args[1]));
        }
        HashSet<State> history = new HashSet<State>();
        boolean repetition = false;
        State s = new State();
        long startTime = System.nanoTime();
        Move coup;
        while (!s.isOver() && !repetition){
            if(s.getTurn() == 'b'){
                coup = aBleu.getBestMove(s,s.getTurn());
            }
            else{
                coup = aRouge.getBestMove(s, s.getTurn());
            }

            
            s = s.play(coup);
            System.out.println(coup);
            s.printBoard();
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