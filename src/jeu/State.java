package jeu;
import java.util.HashSet;

public class State {

    public char [][] board;
    private char turn;
    private char player;
    private int nbPionRouge;
    private int nbPionBleu;

    /*Construtceur pour initialiser le board */
    public State(){
        this.board = new char[7][7];
        this.board[0][0] = 'r';
        this.board[0][6] = 'b';
        this.board[6][6] = 'r';
        this.board[6][0] = 'b';
        this.nbPionRouge = 2;
        this.nbPionBleu = 2;
        this.turn = 'b';

    }
    /*Constructeur pour jouer les coups */
    public State(int pionBleu,int pionRouge,char[][] board,char turn,char player){
        this.nbPionBleu = pionBleu;
        this.nbPionRouge = pionRouge;
        this.board = board;
        this.turn = turn;
        this.player = player;

    }

    public boolean isOver(){
        if ((this.nbPionRouge == 0)|| (this.nbPionBleu == 0)){
            return true;
        }
        return false;
    }

    public HashSet<Move> getMove(){
        HashSet<Move> legalMove = new HashSet<Move>();
            /*Parcours de toutes les cases du plateau */
            for(int i = 0; i<7;i++){
                for(int j = 0;j<7;j++){
                    if (this.board[i][j] == this.turn){
                        /*Parcours des cases autour de la couleur du joueur */
                        for (int k = -1; k<2;k++){
                            for (int l = -1;l<2;l++){
                                /* on regarde si les coordonnées existent pour les clonages*/
                                if ((0<=(i+k) && (i+k)<=6) && (0<=(j+l) && (j+l)<=6)){
                                    if (this.board[i+k][j+l]== '\0'){/*\0 est équivalent au charactère null c'est une case vide du plateau */
                                        int [] posDepart = {i,j};
                                        int[] posArrivee = {i+k,j+l};
                                        legalMove.add(new Move(posDepart,posArrivee,false));
                                    }
                                }
                                /* on regarde si les coordonnées existent pour les sauts*/
                                if ((0<=(i+k*2) && (i+k*2)<=6) && (0<=(j+l*2) && (j+l*2)<=6)){
                                    if (this.board[i+k*2][j+l*2]=='\0'){
                                        int [] posDepart = {i,j};
                                        int [] posArrivee = {i+k*2,j+l*2};
                                        legalMove.add(new Move(posDepart,posArrivee,true));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        
        return legalMove;
    }

    public void printLegalMove(HashSet<Move> legalMove){
        for(Move move : legalMove){
            System.out.println(move);
        }
    }

    public State play(){
        State newState = new State(this.nbPionBleu,this.nbPionRouge,this.board.clone(),this.turn,this.player);
        return newState;
    }

}