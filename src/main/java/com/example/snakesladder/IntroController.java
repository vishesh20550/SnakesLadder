package com.example.snakesladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class IntroController{
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    public void onPwmCLick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
}