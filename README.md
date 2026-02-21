# Blackjack-Gui

## Description
Implémentation d’un jeu de Blackjack avec module de cartes réutilisable.
Projet universitaire de conception et développement Java 11  en client lourd.
Les bonnes pratiques (POO, Design patterns) ont été appliqués pour garantir la maintenabilité et l’évolutivité du projet.


## Structure du projet
- `livraison/cartes/` : module pour modéliser les cartes
- `livraison/blackjack/` : jeu principal
- `livraison/blackjack/dist/cartes.jar` : jar nécessaire pour compiler `blackjack`
- `.gitignore` : ignore les fichiers générés, rapports et binaires

## Pré-requis
- Java 11+ installé
- Eclipse (optionnel) pour ouvrir le projet
- Pas besoin de générer la javadoc pour compiler

## Compilation et exécution
- Via IDE : 
	- Ouvrir Eclipse → importer le projet  
	- Ajouter `cartes` en **Classpath** pour `blackjack`  
	- Exécuter `blackjack/src/app/Main.java` pour lancer une partie de Blackjack
	- Exécuter `cartes/src/app/Main.java` pour manipuler un jeu de cartes (mélanger, piocher, etc...)
- Via ANT : 
	- En se positionnant dans le répertoire blackjack/ ou cartes/ en ligne de commande ;
	- `ant reload` : pour compiler, générer une distribution .jar, générer la javadoc (`ant reload` = `ant clean` + `ant compile` + `ant dist` + `ant javadoc`)
	- `ant run` : lancer le programme
- Via la distribution : 
	- Dans les répertoires dist/ de blackjack/ ou cartes/, exécuter `java -jar blackjack.jar` ou `java -jar cartes.jar`

## Notes
- Les fichiers `.class` et dossiers `bin/`, `out/`, `build/`, `doc/` sont ignorés par Git

## Suite
- Possibilité de faire évoluer le projet à l'avenir ; 
	- Disposer d'un menu pour paramétrer une partie (nombre de joueurs, robots)
	- Ajouter visuellement la carte tirée en complément du score
	- Inclure la notion de table de jeu afin que des joueurs puissent rejoindre une table en cours de partie pour se préparer à la partie suivante
	- Inclure la notion d'emplacements (chaise /spots) afin qu'un joueur puisse occuper plusieurs spot en même temps durant une partie
	- Améliorer et approfondir l'apparence visuel du jeu

## Aperçu du jeu

### Partie de Blackjack 
![partie de Blackjack](docs/images/blackjack-gui.png)

### Module de cartes 
![module de cartes](docs/images/cartes-gui.png)

