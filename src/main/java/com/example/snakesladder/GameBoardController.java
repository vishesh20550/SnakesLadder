package com.example.snakesladder;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {
    @FXML
    private ImageView blueToken;

    @FXML
    private ImageView diceImageView;

    @FXML
    private ImageView diceArrow;

    @FXML
    public void onBackClick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
        new IntroController().setAndShowScene(event,fxmlLoader);
    }

    @FXML
    public void onDiceRoll(MouseEvent event){
        diceArrow.setVisible(false);

        Random rand = new Random();
        Thread thread = new Thread(){
        public void run() {
            for (int i = 0; i < 10; i++) {
                File file = new File("src/main/resources/com/example/snakesladder/dice" + (rand.nextInt(6) + 1) + ".png");
                diceImageView.setImage(new Image(file.toURI().toString()));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            diceArrow.setVisible(true);
        }
        };

        thread.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diceArrow.setVisible(true);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(diceArrow);
        translate.setDuration(Duration.millis(400));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(15);
        translate.setByY(-15);
        translate.setAutoReverse(true);
        translate.play();
    }
}
