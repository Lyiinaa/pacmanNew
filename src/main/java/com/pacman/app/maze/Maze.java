package com.pacman.app.maze;

import com.pacman.app.maze.NormalPellet;
import com.pacman.app.maze.PowerPellet;
import com.pacman.app.maze.Pellet;

public class Maze {

    private final int rows;
    private final int cols;
    private final Cell[][] grid;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        initMaze();
    }

    /* =====================
       Initialisation
       ===================== */

    public void initMaze() {

        int[][] layout = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,1,1,1,0,1,0,1,1,1,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
                {1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,0,1,1,0,0,0,0,0,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,1,1,0,1},
                {1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1},
                {1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (layout[r][c] == 1) {
                    grid[r][c] = new Cell(r, c, CellType.WALL);
                } else {
                    grid[r][c] = new Cell(r, c, CellType.EMPTY);
                    grid[r][c].setPellet(new NormalPellet());
                }
            }
        }

        // PowerPelletم
        grid[18][16].setPellet(new PowerPellet());
        grid[3][3].setPellet(new PowerPellet());
        grid[9][3].setPellet(new PowerPellet());
    }


    /*  Utils */

    public boolean isWall(int row, int col) {
        return grid[row][col].isWall();
    }

    public Pellet getPellet(int row, int col) {
        return grid[row][col].getPellet();
    }

    public void eatPellet(int row, int col) {
        if (grid[row][col].hasPellet()) {
            grid[row][col].removePellet();
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    //****************************************************
    public void reset() {
        initMaze();
    }

}






