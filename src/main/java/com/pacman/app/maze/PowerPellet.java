package com.pacman.app.maze;

import com.pacman.app.engine.GameEngine;
import com.pacman.app.characters.PacMan;
import com.pacman.app.powerups.SuperPacMan;
import com.pacman.app.powerups.SpeedBoostPacMan;
import com.pacman.app.powerups.InvinciblePacMan;
import com.pacman.app.ghosts.state.FrightenedState;

public class PowerPellet implements Pellet {

    @Override
    public void onEat(GameEngine engine) {


        engine.activatePowerMode();

        engine.getPacman().addScore(50);

        System.out.println("POWER MODE ACTIVATED!");
    }
}
