Projet réalisé par Harrisson Kobylt, Antoine Lemaitre, Romain Andres.

Il s'agit d'un jeu de contamination sur grille de 7 par 7 jouable à 2 en 1  versus 1.




#####Règles du jeu repris de l'énnoncé#####

Soit une grille vide de 7 × 7 cases et deux joueurs, Rouge et Bleu. Chaque joueur débute la
partie avec deux pions de sa couleur, respectivement en bas à gauche et haut à droite pour
Bleu et dans les deux autres coins pour Rouge. C’est le joueur Bleu qui joue
en premier.

Le gagnant est celui qui a le plus de pions en fin de partie. La partie se termine quand :
    1. un des joueurs n’a plus de pion de sa couleur sur le plateau,
    2. les deux joueurs doivent passer leur tour,
    3. le plateau de jeu revient dans un état qui a déjà été joué.

Lorsque vient son tour de jouer, un joueur choisit un de pions de sa couleur et peut soit le
cloner, soit le faire sauter. Cloner un pion consiste à placer un nouveau pion
sur une case libre à une distance de un (quelle que soit la direction) du pion choisi. Sauter
consiste à déplacer le pion choisir une case libre à une distance de deux dans une des
huit directions possibles, sachant que ce mouvement permet de passer par-dessus un autre
pion quel qu’en soit le propriétaire. Si le joueur ne peut ni cloner un de ses pions, ni en faire
sauter un alors il doit passer son tour.

À l’issue de ce coup, tous les pions adverses qui sont adjacents au pion déplacé par un saut,
ou adjacent au pion nouvellement créé par clonage sont infectés et sont transformés en des
pions de la couleur du joueur actif. Après cela, c’est au joueur suivant de jouer.

#####Comment lancer le jeu ?#####

##Version sur console##
Taper les commandes suivantes dans un cmd: (se placer dans le repertoire où se situe le fichier readMe.txt)
javac -d bin src/jeu/*.java
java -cp bin jeu.Main
