package com.pacman.app.engine;

import com.pacman.app.characters.Direction;
import com.pacman.app.characters.PacMan;
import com.pacman.app.characters.Ghost;
import com.pacman.app.ghosts.state.ChaseState;
import com.pacman.app.ghosts.state.FrightenedState;
import com.pacman.app.ghosts.strategy.AggressiveStrategy;
import com.pacman.app.ghosts.strategy.DefensiveStrategy;
import com.pacman.app.ghosts.strategy.RandomStrategy;
import com.pacman.app.maze.Maze;
import com.pacman.app.maze.Pellet;
import com.pacman.app.powerups.SuperPacMan;
import com.pacman.app.powerups.SpeedBoostPacMan;
import com.pacman.app.powerups.InvinciblePacMan;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameEngine implements PowerModeObserver {

    private static final Logger log =
            LoggerFactory.getLogger(GameEngine.class);

    private GameState gameState;
    private boolean powerModeActive = false;


    // Grid size
    private final int rows = 21;
    private final int cols = 19;

    // Maze
    private final Maze maze;

    // Pac-Man
    private PacMan pacman;
    private PacMan basePacman;

    // Ghosts
    private final List<Ghost> ghosts;

    private final PowerModeTimer powerModeTimer;


    public GameEngine() {
        this.gameState = GameState.MENU;

        // Pac-Man 
        this.pacman = new PacMan(10, 9);

        // Maze
        this.maze = new Maze(rows, cols);

        // Ghosts
        this.ghosts = new ArrayList<>();
        ghosts.add(new Ghost(1, 1, new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 2, new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 3, new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 4, new ChaseState(new AggressiveStrategy())));

        this.powerModeTimer = new PowerModeTimer();
        powerModeTimer.addObserver(this);
    }

    /* =====================
       Game State management
       ===================== */

    public void startGame() {
        log.info("Game started");
        log.info("STATE Game: MENU -> PLAYING");
        gameState = GameState.RUNNING;
    }

    public void pauseGame() {
        gameState = GameState.PAUSED;
    }

    public void resumeGame() {
        gameState = GameState.RUNNING;
    }

    public void winGame() {
        gameState = GameState.WIN;
    }

    public void loseGame() {
        gameState = GameState.LOSE;
    }


    //------------COLLISIONS
    private void checkCollisions() {

        for (int i = 0; i < ghosts.size(); i++) {

            Ghost ghost = ghosts.get(i);

            boolean sameCell =
                    ghost.getPosition().getRow() == pacman.getPosition().getRow() &&
                            ghost.getPosition().getCol() == pacman.getPosition().getCol();

            if (!sameCell) continue;
            log.info("[COLLISION] PacMan collided with Ghost");


            //  PacMan eats ghost
            if (pacman.canEatGhosts()) {
                ghosts.remove(ghost);
                pacman.addScore(200);
                System.out.println(" Ghost eaten!");
                log.info("[ENTITY] Ghost destroyed");
                return;
            }
            //  PacMan invincible
            if (pacman.isInvincible()) {
                System.out.println("PacMan invincible ");
                return;
            }

            //  PacMan dies
            pacman.loseLife();
            pacman.resetScore();
            System.out.println(" PacMan died! Lives = " + pacman.getLives());
            log.info("[STATE] PacMan lives = {}", pacman.getLives());

            //  GAME OVER
            if (pacman.getLives() <= 0) {
                gameState = GameState.LOSE;
                System.out.println(" GAME OVER");
                log.info("STATE Game: PLAYING -> GAME_OVER");
                log.info("Final score: {}", pacman.getScore());

                return;
            }

            //  Reset positions (still alive)
            resetPositions();
            return;
        }
    }

    //------------RESET POSITION
    private void resetPositions() {

        pacman.moveTo(10, 9);

        ghosts.forEach(Ghost::reset);

        System.out.println("🔄 Positions reset");
    }




    /* =====================
       Update loop
       ===================== */

    public void update() {
        if (gameState != GameState.RUNNING) return;

        movePacman();

        for (Ghost ghost : ghosts) {
            ghost.update(this);
        }
        checkCollisions();
        checkWin();
    }

    /* =====================
       Pac-Man movement
       ===================== */

    private void movePacman() {

        int currentRow = pacman.getPosition().getRow();
        int currentCol = pacman.getPosition().getCol();

        int newRow = currentRow;
        int newCol = currentCol;

        switch (pacman.getDirection()) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
            case NONE -> { return; }
        }

        if (newRow >= 0 && newRow < rows &&
                newCol >= 0 && newCol < cols &&
                !maze.isWall(newRow, newCol)) {

            pacman.moveTo(newRow, newCol);

            Pellet pellet = maze.getPellet(newRow, newCol);
            if (pellet != null) {
                pellet.onEat(this);
                maze.eatPellet(newRow, newCol);
            }
        }
    }

    /* =====================
       POWER MODE (Decorator logic)
       ===================== */

    public void activatePowerMode() {
        powerModeActive = true;

        if (basePacman == null) {
            basePacman = pacman;
        }
        PacMan p = pacman;

        // Decorators stack
        p = new SuperPacMan(p);
        p = new SpeedBoostPacMan(p);
        p = new InvinciblePacMan(p);

        setPacman(p);

        //  Ghosts
        ghosts.forEach(g -> g.setState(new FrightenedState(new RandomStrategy())));

        pacman.addScore(50);

        powerModeTimer.start(10);
        log.info("[DECORATOR] Invincible applied to PacMan (10s)");
        log.info("[DECORATOR] Super applied to PacMan (10s)");
        log.info("[DECORATOR] SpeedBoost applied to PacMan (10s) | speed=80_000_000 ns/tick");




        System.out.println(" POWER MODE STARTED");
    }
    @Override
    public void onPowerModeEnd() {
        powerModeActive = false;
        System.out.println(" POWER MODE ENDED");

        pacman = basePacman;
        basePacman = null;

        ghosts.forEach(g -> g.setState(new ChaseState(new AggressiveStrategy())));

        log.info("[DECORATOR] PowerMode removed from PacMan");
        log.info("[DECORATOR] speed=150_000_000 ns/tick");

    }

    /* =====================
       Getters / setters
       ===================== */

    public PacMan getPacman() {
        return pacman;
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }
    public int getScore() {
        return pacman.getScore();
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void setPacmanDirection(Direction direction) {
        pacman.setDirection(direction);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    //********************************************************
    private void checkWin() {

        boolean anyPelletLeft = false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze.getGrid()[r][c].hasPellet()) {
                    anyPelletLeft = true;
                    break;
                }
            }
        }

        if (!anyPelletLeft) {
            gameState = GameState.WIN;
            System.out.println("🏆 YOU WIN");
        }
    }
    //***************************************
    public void togglePause() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED;
            System.out.println("⏸️ PAUSED");
            log.info("STATE Game: PLAYING -> PAUSED");
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.RUNNING;
            System.out.println("▶️ RESUMED");
            log.info("STATE Game: PAUSED -> PLAYING");
        }
    }

    public void restartGame() {
        System.out.println("🔄 RESTART");

        gameState = GameState.RUNNING;

        pacman = new PacMan(10, 9);
        ghosts.clear();
        ghosts.add(new Ghost(1, 1,
                new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 2,
                new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 3,
                new ChaseState(new AggressiveStrategy())));

        ghosts.add(new Ghost(1, cols - 4,
                new ChaseState(new AggressiveStrategy())));



        maze.reset();
    }
    public long getTickDelay() {
        return powerModeActive ? 80_000_000 : 150_000_000;
    }




}
