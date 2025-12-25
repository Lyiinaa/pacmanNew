# Pac-Man JavaFX

## Description

Jeu **Pac-Man** développé en **Java 17 avec JavaFX**.
Le joueur contrôle Pac-Man dans un labyrinthe, mange des pellets, évite ou attaque les fantômes selon les **power-ups** activés.
Le jeu inclut :

* Déplacement en temps réel
* Fantômes avec comportements intelligents
* Système de score et de vies
* Power Mode temporaire
* Pause, redémarrage, écran **Game Over** et **Win**

L’architecture est basée sur plusieurs **Design Patterns** pour assurer un code modulaire et extensible.



## Membres du Groupe(4 membres)

* Eya Jmili
* Amal Manai
* Wafa Manai
* Lina Hidoussi



## Technologies Utilisées

* **Langage** : Java 17
* **Framework GUI** : JavaFX 21
* **Logging** : SLF4J + Logback
* **Build** : Maven



## Design Patterns Implémentés

1. **State Pattern**
   Gestion des états des fantômes ('ChaseState', 'ScatterState', 'FrightenedState') avec changement dynamique de comportement.

2. **Strategy Pattern**
   Algorithmes de déplacement des fantômes ('AggressiveStrategy', 'DefensiveStrategy', 'RandomStrategy').

3. **Decorator Pattern**
   Système de power-ups pour Pac-Man ('SuperPacMan', 'SpeedBoostPacMan', 'InvinciblePacMan') appliqués dynamiquement.

4. **Observer Pattern**
   Gestion du **Power Mode Timer** avec notification automatique de la fin du mode spécial ('PowerModeTimer' → 'GameEngine').




## Installation

### Prérequis

* JDK 17 ou supérieur
* Maven 3.6+

### Étapes

1. Cloner le dépôt :


git clone https://github.com/Lyiinaa/pacmanNew.git


2. Compiler le projet :

mvn clean install


3. Exécuter le jeu :

mvn javafx:run


---

## Utilisation

* **Flèches directionnelles** : Déplacement de Pac-Man
* **P** : Pause / Reprendre
* **R** : Redémarrer la partie

---

## Structure du Projet


com.pacman.app
├── characters      (PacMan, Ghost, Direction,PacManDecorator)
├── engine          (GameEngine, GameState, PowerModeObserver, PowerModeTimer)
├── ghosts
│   ├── state       (State Pattern)
│   └── strategy    (Strategy Pattern)
├── maze            (Maze, Cell,CellType, Pellet,NormalPellet,PowerPellet)
├── powerups        (Decorator Pattern)
├── ui              (JavaFX Views)
└── utils           (Classes utilitaires:point)



