package com.pacman.app.maze;

import com.pacman.app.engine.GameEngine;

public class NormalPellet implements Pellet {

    @Override
    public void onEat(GameEngine engine) {
        engine.getPacman().addScore(10);
    }
}
