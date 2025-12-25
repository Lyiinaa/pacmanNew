package com.pacman.app.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuView extends VBox {

    public MenuView(Runnable onPlay) {

        setSpacing(30);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: black;");

        Label title = new Label("PAC-MAN");
        title.setTextFill(Color.YELLOW);
        title.setFont(Font.font("Arial Black", 48));

        Button playBtn = new Button("▶ PLAY");
        playBtn.setFont(Font.font(24));
        playBtn.setStyle("""
            -fx-background-color: yellow;
            -fx-text-fill: black;
            -fx-background-radius: 20;
            -fx-padding: 15 40;
        """);

        playBtn.setOnAction(e -> onPlay.run());

        getChildren().addAll(title, playBtn);
    }
}
