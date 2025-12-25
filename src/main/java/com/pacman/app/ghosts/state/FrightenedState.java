package com.pacman.app.ghosts.state;

import com.pacman.app.characters.Ghost;
import com.pacman.app.engine.GameEngine;
import com.pacman.app.ghosts.strategy.GhostStrategy;

public class FrightenedState implements GhostState {

    private final GhostStrategy strategy;

    public FrightenedState(GhostStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void execute(Ghost ghost, GameEngine engine) {
        strategy.move(ghost, engine);
    }
}
