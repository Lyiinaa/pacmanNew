package com.pacman.app.ghosts.strategy;

import com.pacman.app.characters.Ghost;
import com.pacman.app.engine.GameEngine;

public interface GhostStrategy {
    void move(Ghost ghost, GameEngine engine);
}
