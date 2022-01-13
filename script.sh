#!/bin/sh
echo "La compilation s'est bien lancé"
echo "                     "
javac -d bin src/jeu/*.java 
java -cp bin jeu.Main