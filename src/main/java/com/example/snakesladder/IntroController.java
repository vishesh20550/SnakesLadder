package com.example.snakesladder;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    private MediaPlayer player ;

    @FXML
    AnchorPane main;
    @FXML
    ImageView logoImageView;
    @FXML
    ImageView pwm;

    @FXML
    public void onPwmCLick(MouseEvent event){

        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/Button.mp3").toUri().toString());
        play(media);


        Media media1= new Media(Paths.get("src/main/resources/com/example/snakesladder/game_start.mp3").toUri().toString());
        play(media1);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameBoard.fxml"));
        setAndShowScene(event, fxmlLoader);
    }


    private void selectTrack(Media media){
        this.player = new MediaPlayer(media);
    }
    private void play(Media media) {
        if (player != null) {
            player.stop();
        }
        selectTrack(media);
        player.play();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/bg_UI.mp3").toUri().toString());
        play(media);

        ScaleTransition scale = new ScaleTransition(Duration.millis(1000));
        scale.setNode(pwm);

        scale.setFromX(0.0);
        scale.setFromY(0.0);
        scale.setToX(1);
        scale.setToY(1);



        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(logoImageView);
        translate1.setDuration(Duration.millis(1000));
        translate1.setByY(99);


        ParallelTransition para = new ParallelTransition(scale,translate1);
        para.play();


    }
}