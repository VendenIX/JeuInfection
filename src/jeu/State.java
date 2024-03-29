package jeu;
import java.util.ArrayList;
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
    public char getOppositeColor(){
        if(this.turn == 'b'){
            return 'r';
        }
        else{
            return 'b';
        }
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
    /**
     * Augmente le nombre de pion du joueur color
     * @param color char couleur du joueur
     */
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
    /**
     * Diminue le nombre de pion du joueur color
     * @param color char couleur du joueur
     */
    public void decreaseNumberPawns(char color){
        if (color == 'b'){
            this.nbPionBleu--;
        }
        else{
            this.nbPionRouge--;
        }
    }

    /** Renvoie un coup légal aléatoire */
    public Move getRandomMove(){
        
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
    /**
     * Fonction qui retourne tous les coups légaux dans la position
     * @return Hashset de Move
     */
    public HashSet<Move> getMove(){
        HashSet<Move> legalMove = new HashSet<Move>();
        HashSet<Move> legalDup = new HashSet<Move>();
            /*Parcours de toutes les cases du plateau */
            for(int i = 0; i<7;i++){
                for(int j = 0;j<7;j++){
                    if (this.board[i][j] == this.turn){
                        /*Parcours des cases autour de la couleur du joueur */
                        for (int k = -1; k<2;k++){
                            for (int l = -1;l<2;l++){
                                /* on regarde si les coordonnées existent pour les clonages*/
                                if (this.isLegalDuplicate(i, j, k, l)){
                                    legalDup.add(new Move(i,j,i+k,j+l,false));
                                }
                                /* on regarde si les coordonnées existent pour les sauts*/
                                if (this.isLegalJump(i, j, k*2, l*2)){
                                    legalMove.add(new Move(i,j,i+k*2,j+l*2,true));
                                }
                            }
                        }
                    }
                }
            }
        legalDup = this.supprimeDoublon(legalDup);
        legalMove.addAll(legalDup);
        return legalMove;
    }

    /**
     * Fonction qui retourne un ArrayList des coups légaux triés selon s'ils infectent des pions ou non
     * @return ArrayList des moves legaux
     */
    public ArrayList<Move> getArrayMove(){
        HashSet<Move> ensMoves = getMove();
        return this.triInfection(ensMoves, this.turn);
    }
    /**
     * Vérifie si le coup de saut est légal
     * @param i position x du deplacement du pion 
     * @param j position y du deplacement du pion 
     * @param k position x du deplacement du pion
     * @param l position y du deplacement du pion
     * @requires k à distance de saut c'est à dire -2 ou 2
     * @requires l à distance de saut c'est à dire -2 ou 2
     * @return True si le coup est legal False si le coup est illegal
     */
    public boolean isLegalJump(int i,int j, int k , int l){
        if ((0<=(i+k) && (i+k)<=6) && (0<=(j+l) && (j+l)<=6)){
            if (this.board[i+k][j+l]=='\0'){/*\0 est équivalent au charactère null c'est une case vide du plateau */
                return true;
            }
        }
        return false;
    }
    /**
     * Vérifie si le coup de duplication est légalsaut
saut
saut
     * @param i position x d'origine du pion 
     * @param j position y d'origine du pion 
     * @param k position x de destination du pion
     * @param l position y de destination du pion
     * @requires k à distance de duplication c'est à dire -1 ou 1
     * @requires l à distance de duplication c'est à dire -1 ou 1
     * @return True si le coup est legal False si le coup est illegal
     */
    public boolean isLegalDuplicate(int i,int j, int k , int l){
        if ((0<=(i+k) && (i+k)<=6) && (0<=(j+l) && (j+l)<=6)){
            if (this.board[i+k][j+l]=='\0'){
                return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui détermine si le coup coup infecterait la couleur color
     * @param coup Move 
     * @param color char couleur à infecter
     * @return true si lorsque que le coup sera joué il y aura infection false autrement
     */
    public boolean isInfectionMove(Move coup, char color){
        return this.isInfectionMove(coup.getEnd()[0],coup.getEnd()[1], color);
    }
    /**
     * Fonction qui détermine si la case i,j infecterait la couleur color
     * @param i coord x de la case d'arrivee
     * @param j coord y de la case d'arrivee
     * @param color char couleur à infecter
     * @return true si lorsque que la case sera prise il y aura infection false autrement
     */
    public boolean isInfectionMove(int i,int j,char color){
        for (int k = -1; k<2;k++){
            for (int l = -1;l<2;l++){
                if ((0<=(i+k) && (i+k)<=6) && (0<=(j+l) && (j+l)<=6)){
                    if (this.board[i+k][j+l] == color){ /**Si la couleur de l'adversaire est détectée alors on infecte la cases  */
                        return true;
                    }
                }
            }
        }
        return false;
        
    }
    /**
     * Algo de tri pour obtenir un array où les premiers coups sont ceux qui capturent des pions ennemis de couleur color
     * @param ensembleCoup
     * @param color char couleur des pions du joueur
     * @return arrayList trié des coups avec ceux qui capturent en premier
     */
    public ArrayList<Move> triInfection(HashSet<Move> ensembleCoup,char color){
        ArrayList<Move> res = new ArrayList<Move>();
        ArrayList<Move> resteMoves = new ArrayList<Move>();
        if(color == 'b'){
            for(Move coup:ensembleCoup){
                if(this.isInfectionMove(coup, 'r')){
                    res.add(coup);
                }
                else{
                    resteMoves.add(coup);
                }
            }
        res.addAll(resteMoves);
        }
        else{
            for(Move coup:ensembleCoup){
                if(this.isInfectionMove(coup, 'b')){
                    res.add(coup);
                }
                else{
                    resteMoves.add(coup);
                }
            }
        res.addAll(resteMoves);
        }
        return res;
    }
    /**
     * Prends un Hashset de Move et les compare afin de retourner un set de move tous différent
     * @param ensembleCoup HashSet de Move
     * @requires ensembleCoup != null
     * @ensures res != None
     */
    public HashSet<Move> supprimeDoublon(HashSet<Move> ensembleCoup){

        HashSet<Move> res = new HashSet<>();
        boolean same = false;
        for (Move coup : ensembleCoup){
            same = false;
            for(Move coupbis : res){
                if (coupbis.isSameDuplication(coup)){
                    same = true;
                }
            }
            if (!same){
                res.add(coup);
            }
        }
        return res;
    }

    public void printLegalMove(HashSet<Move> legalMove){
        for(Move move : legalMove){
            System.out.println(move);
        }
    }
    public void printLegalMove(ArrayList<Move> legalMove){
        for(int i = 0;i<legalMove.size();i++){
            System.out.println(legalMove.get(i));
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
        /**Toutes les cases adjacentes à cases de couleur color changeront de couleur
        @params cases : cases à l'origine de l'infection
        @params color : charactère de la couleur infectable
         */
    public void infection(int[] cases,char color){

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
    /**
     * Fonction qui retourne un nouvel état après avoir joué le coup 
     * @param coup move
     * @return nouvelEtat : State
     */
    public State play(Move coup){
        State newState = new State(this.nbPionBleu,this.nbPionRouge,this.copieTableau(),this.turn,this.player,this.nbTurn+1);

        /** Si c'est un saut on retire le pion de sa case */
        if (coup != null){
            if (coup.getJump()){
                newState.board[coup.getStart()[0]][coup.getStart()[1]]='\0';
                newState.decreaseNumberPawns(newState.getTurn());
            }
            newState.board[coup.getEnd()[0]][coup.getEnd()[1]] = newState.getTurn();
            newState.increaseNumberPawns(newState.getTurn());
            if (newState.getTurn() == 'r'){
                int [] posArrivee = {coup.getEnd()[0],coup.getEnd()[1]};
                newState.infection(posArrivee,'b');
            }
            else
            {
                int [] posArrivee = {coup.getEnd()[0],coup.getEnd()[1]};
                newState.infection(posArrivee,'r');
            }
        }

        newState.changeTurn();
        return newState;
    }

    /**
     * Fonction qui calcule le score du joueur donné en paramètre
     * @param player char couleur du joueur
     * @require player = 'b' || player = 'r'
     * @return Double score du joueur
     */
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