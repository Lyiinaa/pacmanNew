package com.pacman.app.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EndView extends VBox {

    public EndView(String message, Color color, Runnable onRestart) {

        setSpacing(25);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: black;");

        Label title = new Label(message);
        title.setTextFill(color);
        title.setFont(Font.font("Arial Black", 42));

        Button restartBtn = new Button("🔁 RESTART");
        restartBtn.setFont(Font.font(22));
        restartBtn.setStyle("""
            -fx-background-color: white;
            -fx-text-fill: black;
            -fx-background-radius: 20;
            -fx-padding: 12 35;
        """);

        restartBtn.setOnAction(e -> onRestart.run());

        getChildren().addAll(title, restartBtn);
    }
}
