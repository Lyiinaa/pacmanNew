package com.pacman.app.powerups;

import com.pacman.app.characters.PacMan;
import com.pacman.app.characters.PacManDecorator;

public class SuperPacMan extends PacManDecorator {

    public SuperPacMan(PacMan pacman) {
        super(pacman);
    }

    @Override
    public boolean canEatGhosts() {
        return true;
    }

}
