package jeu;
import java.util.HashSet;
import java.util.Random;

public class State {

    public char [][] board;
    private char turn;
    private char player;
    public int nbPionRouge;
    public int nbPionBleu;
    private int nbTurn;

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
        this.nbTurn = 0;
        this.player= 'b';

    }
    /*Constructeur pour jouer les coups */
    public State(int pionBleu,int pionRouge,char[][] board,char turn,char player, int nbTurn){
        this.nbPionBleu = pionBleu;
        this.nbPionRouge = pionRouge;
        this.board = board;
        this.turn = turn;
        this.player = player;
        this.nbTurn = nbTurn;

    }

    public char getPlayer(){
        return this.player;
    }
    public char getTurn(){
        return this.turn;
    }

    public int getNbTurn()
    {
        return this.nbTurn;
    }
    public void changeTurn(){
        if (this.turn == 'r'){
            this.turn = 'b';
        }
        else{
            this.turn = 'r';
        }
        
    }

    public char[][] copieTableau(){
        /**Copie le board de l'etat sans utiliser clone()
         * @return un tableau 2d identique au board
         */
        char[][] resultat = new char[7][7];
        for(int i = 0;i < this.board.length;i++){
            for(int j = 0;j < this.board[i].length;j++){
                resultat[i][j] = this.board[i][j];
            }
        }
        return resultat;
    }

    public boolean sameBoard(char[][] otherBoard ){
        /**Compare le tableau d'un autre et retourne true si les tableaux sont identiques */
        for(int i = 0;i < this.board.length;i++){
            for(int j = 0;j < this.board[i].length;j++){
                if (otherBoard[i][j] != this.board[i][j]){
                    return false;
                } 
            }
        }
        return true;
    }
    public void increaseNumberPawns(char color){
        if (color == 'b'){
            this.nbPionBleu++;
        }
        else{
            this.nbPionRouge++;
        }
    }
    public boolean isOver(){
        if ((this.nbPionRouge == 0)|| (this.nbPionBleu == 0)){
            return true;
        }
        return false;
    }

    public void decreaseNumberPawns(char color){
        if (color == 'b'){
            this.nbPionBleu--;
        }
        else{
            this.nbPionRouge--;
        }
    }

    public Move getRandomMove(){
        /** Renvoie un coup légal aléatoire */
        HashSet<Move> ensembleCoup = this.getMove();
        if (ensembleCoup.isEmpty()){
            return null;
        }
        Random rand = new Random();
        int num = rand.nextInt(ensembleCoup.size());
        int i = 0;
        for(Move coup: ensembleCoup){

            if (i == num){
                return coup;
            }
            i++;
        }
        return null;
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

    public void printBoard(){
        System.out.println("State's board :");
        for(int i = 0;i < this.board.length;i++){
            for(int j = 0;j < this.board[i].length;j++){
                if(this.board[i][j] == '\0'){
                    System.out.print('.');
                }
                else{
                    System.out.print(this.board[i][j]);
                }
            }
            System.out.println('\n');
        }
    }

    public void infection(int[] cases,char color){
        /**Toutes les casess adjacentes à cases de couleur color changeront de couleur
        @params cases : cases à l'origine de l'infection, color : charactère de la couleur infectable
         */
        if (color == 'r'){
            for (int k = -1; k<2;k++){
                for (int l = -1;l<2;l++){
                    if ((0<=(cases[0]+k) && (cases[0]+k)<=6) && (0<=(cases[1]+l) && (cases[1]+l)<=6)){
                        if (this.board[cases[0]+k][cases[1]+l] == color){ /**Si la couleur de l'adversaire est détectée alors on infecte la cases  */
                            this.board[cases[0]+k][cases[1]+l] = 'b';
                            this.nbPionBleu ++;
                            this.nbPionRouge--;
                        }
                    }
                }
            }
        }
        else{
            for (int k = -1; k<2;k++){
                for (int l = -1;l<2;l++){
                    if ((0<=(cases[0]+k) && (cases[0]+k)<=6) && (0<=(cases[1]+l) && (cases[1]+l)<=6)){
                        if (this.board[cases[0]+k][cases[1]+l] == color){ /**Si la couleur de l'adversaire est détectée alors on infecte la cases  */
                            this.board[cases[0]+k][cases[1]+l] = 'r';
                            this.nbPionBleu--;
                            this.nbPionRouge++;
                        }
                    }
                }
            }
        }
    }

    public State play(Move coup){
        State newState = new State(this.nbPionBleu,this.nbPionRouge,this.copieTableau(),this.turn,this.player,this.nbTurn+1);

        /** Si c'est un saut on retire le pion de sa case */
        if (coup != null){
            if (coup.jump){
                newState.board[coup.start[0]][coup.start[1]]='\0';
                newState.decreaseNumberPawns(newState.getTurn());
            }
            newState.board[coup.end[0]][coup.end[1]] = newState.getTurn();
            newState.increaseNumberPawns(newState.getTurn());
            if (newState.getTurn() == 'r'){
                int [] posArrivee = {coup.end[0],coup.end[1]};
                newState.infection(posArrivee,'b');
            }
            else
            {
                int [] posArrivee = {coup.end[0],coup.end[1]};
                newState.infection(posArrivee,'r');
            }
        }

        newState.changeTurn();


        return newState;
    }

    // on veut le score du player
    public Double getScore(char player)
    {
        if(player == 'b')
        {
            return (double) this.nbPionBleu/(this.nbPionRouge+this.nbPionBleu);
        }
        else{
            return (double) this.nbPionRouge/(this.nbPionBleu+this.nbPionRouge);
        }
    }

}