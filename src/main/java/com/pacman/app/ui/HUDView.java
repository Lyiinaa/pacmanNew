package com.pacman.app.ui;

import com.pacman.app.engine.GameEngine;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUDView extends HBox {

    private final Label scoreLabel;
    private final Label livesLabel;
    private final GameEngine engine;

    public HUDView(GameEngine engine) {
        this.engine = engine;

        setSpacing(30);
        setStyle("-fx-background-color: black; -fx-padding: 10;");

        scoreLabel = new Label();
        livesLabel = new Label();

        scoreLabel.setTextFill(Color.YELLOW);
        livesLabel.setTextFill(Color.RED);

        scoreLabel.setFont(Font.font(18));
        livesLabel.setFont(Font.font(18));

        getChildren().addAll(scoreLabel, livesLabel);

        update();
    }

    public void update() {
        scoreLabel.setText("Score : " + engine.getPacman().getScore());
        livesLabel.setText("❤️ Lives : " + engine.getPacman().getLives());
    }
}
