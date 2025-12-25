package com.pacman.app.ghosts.strategy;

import com.pacman.app.characters.Ghost;
import com.pacman.app.engine.GameEngine;

public class AggressiveStrategy implements GhostStrategy {

    @Override
    public void move(Ghost ghost, GameEngine engine) {

        int gRow = ghost.getPosition().getRow();
        int gCol = ghost.getPosition().getCol();

        int pRow = engine.getPacman().getPosition().getRow();
        int pCol = engine.getPacman().getPosition().getCol();

        int newRow = gRow;
        int newCol = gCol;

        if (pRow < gRow) newRow--;
        else if (pRow > gRow) newRow++;

        if (pCol < gCol) newCol--;
        else if (pCol > gCol) newCol++;

        if (!engine.getMaze().isWall(newRow, newCol)) {
            ghost.moveTo(newRow, newCol);
        }
    }
}
