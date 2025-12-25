package com.pacman.app.ui;

import com.pacman.app.engine.GameEngine;
import com.pacman.app.characters.Direction;
import com.pacman.app.engine.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log =
            LoggerFactory.getLogger(GameEngine.class);
    private Scene scene;
    private GameEngine engine;
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage stage) {

        showMenu(stage);

        stage.setTitle("Pac-Man");
        stage.show();
    }

    /* =====================
       MENU
       ===================== */

    private void showMenu(Stage stage) {

        MenuView menu = new MenuView(() -> startGame(stage));

        scene = new Scene(menu, 610, 700);
        stage.setScene(scene);
    }

    /* =====================
       GAME
       ===================== */

    private void startGame(Stage stage) {

        engine = new GameEngine();
        engine.startGame();

        HUDView hud = new HUDView(engine);
        GameView gameView = new GameView(engine);

        VBox gameLayout = new VBox(hud, gameView);
        gameLayout.setStyle("-fx-background-color: black;");

        PauseOverlayView pauseView = new PauseOverlayView(
                engine::togglePause,
                engine::restartGame
        );
        pauseView.setVisible(false);

        StackPane root = new StackPane(gameLayout, pauseView);

        scene.setRoot(root);

        /* =====================
           KEYBOARD
           ===================== */
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> engine.setPacmanDirection(Direction.UP);
                case DOWN -> engine.setPacmanDirection(Direction.DOWN);
                case LEFT -> engine.setPacmanDirection(Direction.LEFT);
                case RIGHT -> engine.setPacmanDirection(Direction.RIGHT);
                case P, ESCAPE -> engine.togglePause();
                case R -> engine.restartGame();
            }
        });

        /* =====================
           GAME LOOP
           ===================== */
        gameLoop = new AnimationTimer() {

            private long last = 0;

            @Override
            public void handle(long now) {

                if (now - last < engine.getTickDelay()) return;

                pauseView.setVisible(engine.getGameState() == GameState.PAUSED);

                if (engine.getGameState() == GameState.RUNNING) {
                    engine.update();
                }

                gameView.render();
                hud.update();

                if (engine.getGameState() == GameState.LOSE) {
                    stop();
                    showEnd(stage, "💀 GAME OVER", Color.RED);
                }

                if (engine.getGameState() == GameState.WIN) {
                    stop();
                    showEnd(stage, "🏆 YOU WIN!", Color.LIME);
                    log.info("STATE Game: PLAYING -> WIN");
                    log.info("Final score: {}", engine.getScore());

                }

                last = now;
            }
        };

        gameLoop.start();
    }

    /* =====================
       END SCREENS
       ===================== */

    private void showEnd(Stage stage, String text, Color color) {

        EndView end = new EndView(text, color, () -> showMenu(stage));
        scene.setRoot(end);
    }

    public static void main(String[] args) {
        launch();
    }
}
