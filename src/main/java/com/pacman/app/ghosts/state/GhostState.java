package com.pacman.app.ghosts.state;

import com.pacman.app.characters.Ghost;
import com.pacman.app.engine.GameEngine;

public interface GhostState {
    void execute(Ghost ghost, GameEngine engine);

}
