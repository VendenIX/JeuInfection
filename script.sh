#!/bin/sh
echo "La compilation s'est bien lancé"
echo "                     "
javac -d bin src/jeu/*.java src/algorithms/*.java
java -cp bin jeu.Main 2 2 "true"