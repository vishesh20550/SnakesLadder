package com.example.snakesladder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {
    @FXML
    private ImageView blueToken;

    @FXML
    private ImageView diceImageView;

    @FXML
    public void onBackClick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
        new IntroController().setAndShowScene(event,fxmlLoader);
    }

    @FXML
    public void onDiceRoll(MouseEvent event){
//        String path=
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
