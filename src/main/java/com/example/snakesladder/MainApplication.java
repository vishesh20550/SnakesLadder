package com.example.snakesladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),400,550);
        stage.setTitle("Snakes and Ladders");
        stage.setResizable(false);
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/bg_UI.mp3").toUri().toString());
        MediaPlayer mediaPlayer= new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        String css = MainApplication.class.getResource("css.css").toExternalForm();
//        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}