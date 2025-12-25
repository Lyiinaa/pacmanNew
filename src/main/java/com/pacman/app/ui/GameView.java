package com.pacman.app.ui;

import com.pacman.app.engine.GameEngine;
import com.pacman.app.maze.Cell;
import com.pacman.app.maze.CellType;
import com.pacman.app.maze.PowerPellet;
import com.pacman.app.characters.Ghost;
import com.pacman.app.ghosts.state.FrightenedState;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameView extends GridPane {

    private static final int CELL_SIZE = 32;
    private final GameEngine engine;

    public GameView(GameEngine engine) {
        this.engine = engine;

        setPrefSize(
                engine.getCols() * CELL_SIZE,
                engine.getRows() * CELL_SIZE
        );
    }

    public void render() {
        getChildren().clear();

        drawMaze();
        drawPacMan();
        drawGhosts();
    }

    /* =====================
       DRAW MAZE
       ===================== */

    private void drawMaze() {

        Cell[][] grid = engine.getMaze().getGrid();

        for (int r = 0; r < engine.getRows(); r++) {
            for (int c = 0; c < engine.getCols(); c++) {

                Cell cell = grid[r][c];

                // Background / Wall
                Rectangle tile = new Rectangle(CELL_SIZE, CELL_SIZE);
                tile.setFill(
                        cell.getType() == CellType.WALL
                                ? Color.DARKBLUE
                                : Color.BLACK
                );
                add(tile, c, r);

                // Pellet

                if (cell.hasPellet()) {

                    double radius =
                            cell.getPellet() instanceof PowerPellet ? 8 : 4;

                    Circle pellet = new Circle(radius);
                    pellet.setFill(
                            cell.getPellet() instanceof PowerPellet
                                    ? Color.LIMEGREEN
                                    : Color.WHITE
                    );
                    pellet.setTranslateX(CELL_SIZE / 2.0);
                    pellet.setTranslateY(CELL_SIZE / 2.0);

                    add(pellet, c, r);
                }


            }
        }
    }

    /* =====================
       DRAW PACMAN
       ===================== */

    private void drawPacMan() {

        int r = engine.getPacman().getPosition().getRow();
        int c = engine.getPacman().getPosition().getCol();

        Circle pacman = new Circle(CELL_SIZE / 2.0 - 2);
        pacman.setFill(Color.YELLOW);
        pacman.setTranslateX(CELL_SIZE / 2.0);
        pacman.setTranslateY(CELL_SIZE / 2.0);

        add(pacman, c, r);
    }

    /* =====================
       DRAW GHOSTS
       ===================== */

    private void drawGhosts() {

        for (Ghost ghost : engine.getGhosts()) {

            int r = ghost.getPosition().getRow();
            int c = ghost.getPosition().getCol();

            Circle g = new Circle(CELL_SIZE / 2.0 - 4);

            if (ghost.getState() instanceof FrightenedState) {
                g.setFill(Color.DODGERBLUE); // 🔵
            } else {
                g.setFill(Color.RED); // 🔴
            }

            g.setTranslateX(CELL_SIZE / 2.0);
            g.setTranslateY(CELL_SIZE / 2.0);

            add(g, c, r);
        }
    }

}
