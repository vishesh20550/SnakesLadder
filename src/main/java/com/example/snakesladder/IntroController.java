package com.example.snakesladder;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    @FXML
    ImageView logoImageView;
    @FXML
    ImageView pwm;
    @FXML
    ImageView pwc;

    @FXML
    public void onPwmCLick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameBoard.fxml"));
        setAndShowScene(event, fxmlLoader);
    }
    public void setAndShowScene(MouseEvent event, FXMLLoader fxmlLoader){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onPwcCLick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameBoard.fxml"));
        setAndShowScene(event, fxmlLoader);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ScaleTransition scale = new ScaleTransition(Duration.millis(1000));
        ScaleTransition scale1 = new ScaleTransition(Duration.millis(1000));
        scale.setNode(pwm);

        scale.setFromX(0.0);
        scale.setFromY(0.0);
        scale.setToX(1);
        scale.setToY(1);

        scale1.setNode(pwc);
        scale1.setFromX(0.0);
        scale1.setFromY(0.0);
        scale1.setToX(1);
        scale1.setToY(1);


        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(logoImageView);
        translate1.setDuration(Duration.millis(1000));
        translate1.setByY(99);


        ParallelTransition para = new ParallelTransition(scale,scale1,translate1);
        para.play();


    }
}