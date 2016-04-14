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


