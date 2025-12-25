package com.pacman.app.characters;

import com.pacman.app.utils.Point;

public abstract class PacManDecorator extends PacMan {

    protected PacMan pacman;

    public PacManDecorator(PacMan pacman) {
        super(
                pacman.getPosition().getRow(),
                pacman.getPosition().getCol()
        );
        this.pacman = pacman;
    }
    @Override public boolean canEatGhosts() { return pacman.canEatGhosts(); }
    @Override public boolean isInvincible() { return pacman.isInvincible(); }
    @Override public int getSpeedMultiplier() { return pacman.getSpeedMultiplier(); }

    @Override
    public Point getPosition() {
        return pacman.getPosition();
    }

    @Override
    public void moveTo(int row, int col) {
        pacman.moveTo(row, col);
    }

    @Override
    public void addScore(int value) {
        pacman.addScore(value);
    }

    @Override
    public int getScore() {
        return pacman.getScore();
    }

    @Override
    public int getLives() {
        return pacman.getLives();
    }
}
