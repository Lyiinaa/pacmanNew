package com.pacman.app.powerups;

import com.pacman.app.characters.PacMan;
import com.pacman.app.characters.PacManDecorator;

public class SpeedBoostPacMan extends PacManDecorator {

    public SpeedBoostPacMan(PacMan pacman) {
        super(pacman);
    }

    @Override
    public int getSpeedMultiplier() {
        return pacman.getSpeedMultiplier() + 1;
    }

}
