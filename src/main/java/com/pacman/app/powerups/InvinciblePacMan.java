package com.pacman.app.powerups;

import com.pacman.app.characters.PacMan;
import com.pacman.app.characters.PacManDecorator;

public class InvinciblePacMan extends PacManDecorator {

    public InvinciblePacMan(PacMan pacman) {
        super(pacman);
    }

    @Override
    public boolean isInvincible() {
        return true;
    }

}
