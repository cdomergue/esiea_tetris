#jTetris


----------


##Crédits
Christophe DOMERGUE et Franck BASTIDE

##Prérequis

 - Java 8
 - Maven

##Lancement

 1. Dans le répertoire racine, faire un `mvn clean package`
 2. Se rendre dans `target/jTetris-0.0.1-SNAPSHOT-release/jTetris-0.0.1-SNAPSHOT`
 3. Sur linux, faire `chmod +x ./game.sh` puis `./game.sh`
 4. Sur windows, cliquer sur `game.bat`
 5. Pour avoir une sauvegarde des scores, exécutez depuis un répertoire ou vous avez les droits ou exécuter-le en administrateur.

##Fonctionnalités

 - Interface graphique
 - Gestion des déplacements par le clavier
 - Gestion des collisions
 - Gestion des rotations
 - Gestion de la suppression de ligne
 - Gestion du score et sauvegarde du Best Score
 - Gestion de la musique

##Comment jouer

 - Se déplacer à gauche : Flèche gauche
 - Se déplacer à droite : Flèche droite
 - Tourner la pièce : Flèche haute
 - Accélerer la descente : Flèche bas
 - Couper / Remettre la musique : M

##Architecture

L'application est composée de deux parties, les composants du jeu dans *components* et l'interface graphique dans *IHM*.

Les composants du jeu sont indépendant de l'IHM et peuvent être exécutés seuls.

L'IHM utilise la bibliothèque Slick2D, bibliothèque puissante basée sur openGL.

Les composants sont testés mais pas l'IHM.

###Tetriminos

Les tetriminos sont composés de plusieurs choses, un ID, une forme (shape) et une position.
La forme est un carré de 4x4 dont chaque cellule (units) est true si une partie de la pièce est dans la case et false sinon. A cette forme est associée une couleur, sous forme de String.

####Tetrimino Builder

Comme les tetriminos sont complexes, il faut pouvoir les construires facilement. Le TetriminoBuilder intervient ici. Il récupère en paramètre un enum TetriminoPiece que l'on a défini et créer la shape correspondant, ajoute un ID (c'est un singleton, l'ID est donc tout le temps incrémenté et différent entre chaque pièces), puis renvoi la nouvelle pièce.

Il est possible de lui demander de créer une pièce aléatoirement parmis les différentes pièces de l'enum.

####Tetrimino Rotater

Une pièce veut pouvoir être pivotée. Le Tetrimino Rotater va pivoter la pièce en fonction du paramètre transmis. Pour cela, il va faire une rotation de la matrice 4x4 en créant un nouveau Shape et en remplaçant l'ancien du tetrimino.

C'est à cause de cela que la rotation en jeu est étrange, pour l'instant nous n'avons pas réussis à corriger ce problème.

###Board

Le Board est le plateau de jeu et est le composant principal, où la logique de jeu a lieue. 
Il contient des cellules. Ces dernière contienne un booléen servant à savoir si elle est remplie, une couleur, et un Tetrimino ID pour savoir à qui cette partie de pièce appartient.

Comme les pièces sont des matrices 4x4, le board va se charger de faire descendre (ou aller à gauche/droite) la pièce (les units) dans la shape une fois que celle-ci a touché un côté ou un autre pièce.

Les collisions sont gérées après coup. Le board tente de déplacer la pièce, et si une unit rencontre un conflit, restaure la pièce à sont état d'origine et lève une exception.

