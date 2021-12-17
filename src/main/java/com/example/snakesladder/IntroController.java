package com.example.snakesladder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IntroController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}