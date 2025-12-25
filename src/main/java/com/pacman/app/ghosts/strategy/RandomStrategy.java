package com.pacman.app.ghosts.strategy;

import com.pacman.app.characters.Ghost;
import com.pacman.app.engine.GameEngine;

import java.util.Random;

public class RandomStrategy implements GhostStrategy {

    private final Random random = new Random();

    @Override
    public void move(Ghost ghost, GameEngine engine) {

        int row = ghost.getPosition().getRow();
        int col = ghost.getPosition().getCol();

        int[][] moves = {
                {row - 1, col},
                {row + 1, col},
                {row, col - 1},
                {row, col + 1}
        };

        int[] chosen = moves[random.nextInt(moves.length)];

        if (!engine.getMaze().isWall(chosen[0], chosen[1])) {
            ghost.moveTo(chosen[0], chosen[1]);
        }
    }
}
