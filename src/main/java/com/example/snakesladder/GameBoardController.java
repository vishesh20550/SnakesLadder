package com.example.snakesladder;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class GameBoardController implements Initializable {
    private int diceRolledFinal;

    @FXML
    private ImageView win;

    @FXML
    private AnchorPane main2;

    @FXML
    private ImageView blueToken;
    @FXML
    private ImageView greenToken;

    @FXML
    private ImageView diceImageView;

    @FXML
    private ImageView diceArrow;

    Player player1= new Player("blueToken");
    Player player2= new Player("greenToken");

    @FXML
    public void onBackClick(MouseEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
        new IntroController().setAndShowScene(event,fxmlLoader);
        System.out.println(diceRolledFinal);
    }

    @FXML
    public void onDiceRoll(MouseEvent event){
        diceArrow.setVisible(false);

        Random rand = new Random();
        Thread thread = new Thread(){
            int diceRolled;
            public void run() {
                for (int i = 0; i < 10; i++) {
                    diceRolled =(rand.nextInt(6) + 1);
                    File file = new File("src/main/resources/com/example/snakesladder/dice" + diceRolled + ".png");
                    diceImageView.setImage(new Image(file.toURI().toString()));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                diceRolledFinal=diceRolled;
                if(player1.isTurn()){
                    try {
                        checkPlayerStatus(player1, blueToken, player2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        checkPlayerStatus(player2, greenToken, player1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                diceArrow.setVisible(true);
            }
        };
        thread.start();
    }

    private void checkPlayerStatus(Player player2, ImageView greenToken, Player player1) throws IOException {
        if(player2.isActive()){
            movePlayer(player2);
        }
        else{
            if (diceRolledFinal==1){
                player2.setActive(true);
                greenToken.setLayoutY(player2.getyCord());
                player2.setTileNumber(1);
            }
        }
        player2.setTurn(false);
        player1.setTurn(true);
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

    public void movePlayer(Player player) throws IOException {
        if(player.getColor().equals("blueToken")){

            movePlayerHelper(player, blueToken);
        }
        else{
            movePlayerHelper(player, greenToken);
        }
    }

    private void movePlayerHelper(Player player, ImageView token) throws IOException {
        if(player.getTileNumber()+diceRolledFinal>100){
            return;
        }
        for(int i=0;i<diceRolledFinal;i++){
            if(((player.getTileNumber()+1)%10==1 )&& (player.getTileNumber()>=10)){
                player.setStepX(-player.getStepX());
                token.setLayoutY(player.getStepY()+ token.getLayoutY());
            }
            else{
                token.setLayoutX(player.getStepX() + token.getLayoutX());
            }
            player.setTileNumber(player.getTileNumber() + 1);
            if(player.getTileNumber()==100){
                System.out.println(player.getColor()+" won");
                onWin(player);
            }
        }
    }
    public void onWin(Player player) throws IOException {
        win.setImage(new Image(getClass().getResourceAsStream("snake.png")));
        win.setOnMouseClicked(mouseEvent -> {
            new IntroController().onPwcCLick(mouseEvent);
        });
    }
}
