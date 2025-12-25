package com.pacman.app.characters;

import com.pacman.app.utils.Point;

public class PacMan {

    private Point position;
    private Direction direction;
    private PacMan basePacman;

    private int score;
    private int lives;

    public PacMan(int startRow, int startCol) {
        this.position = new Point(startRow, startCol);
        this.direction = Direction.NONE;
        this.score = 0;
        this.lives = 3;
    }

    /* =====================
       Movement
       ===================== */

    public void moveTo(int row, int col) {
        position.setRow(row);
        position.setCol(col);
    }

    /* =====================
       Score / Lives
       ===================== */

    public void addScore(int value) {
        score += value;
    }

    public void loseLife() {
        lives--;
    }
    public void resetScore() {
        score = 0;
    }


    /* =====================
       Getters / setters
       ===================== */

    public Point getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
    // =====================
    // Power abilities (DEFAULT)
    // =====================

    public boolean canEatGhosts() {
        return false;
    }

    public boolean isInvincible() {
        return false;
    }

    public int getSpeedMultiplier() {
        return 1;
    }

}
