package com.pacman.app.maze;

import com.pacman.app.utils.Point;
import com.pacman.app.maze.Pellet;
public class Cell {

    private Point position;
    private CellType type;
    private Pellet pellet;

    public Cell(int row, int col, CellType type) {
        this.position = new Point(row, col);
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isWall() {
        return type == CellType.WALL;
    }

    public boolean hasPellet() {
        return pellet != null;
    }
    public Pellet getPellet() {
        return pellet;
    }

    public void setPellet(Pellet pellet) {
        this.pellet = pellet;
    }

    public void removePellet() {
        this.pellet = null;
        this.type = CellType.EMPTY;
    }
}
