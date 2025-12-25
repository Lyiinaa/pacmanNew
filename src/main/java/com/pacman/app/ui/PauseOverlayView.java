package com.pacman.app.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PauseOverlayView extends StackPane {

    public PauseOverlayView(Runnable onResume, Runnable onRestart) {

        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.color(0, 0, 0, 0.6));

        Label title = new Label("⏸ PAUSED");
        title.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");

        Button resume = new Button("▶ Resume");
        Button restart = new Button("🔄 Restart");

        resume.setOnAction(e -> onResume.run());
        restart.setOnAction(e -> onRestart.run());

        VBox box = new VBox(20, title, resume, restart);
        box.setAlignment(Pos.CENTER);

        getChildren().addAll(bg, box);
    }
}
