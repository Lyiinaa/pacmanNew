package com.pacman.app.characters;

import com.pacman.app.engine.GameEngine;
import com.pacman.app.ghosts.state.GhostState;
import com.pacman.app.utils.Point;

public class Ghost {

    private Point position;
    private GhostState state;
    private final Point spawn;

    public Ghost(int row, int col, GhostState initialState) {
        this.position = new Point(row, col);
        this.spawn = new Point(row, col);
        this.state = initialState;
    }

    public void update(GameEngine engine) {
        state.execute(this, engine);

    }
    public void reset() {
        position.setRow(spawn.getRow());
        position.setCol(spawn.getCol());
    }

    /* =====================
       State management
       ===================== */

    public void setState(GhostState state) {
        this.state = state;
    }

    public GhostState getState() {
        return state;
    }


    /* =====================
       Position
       ===================== */

    public Point getPosition() {
        return position;
    }

    public void moveTo(int row, int col) {
        position.setRow(row);
        position.setCol(col);
    }
}
