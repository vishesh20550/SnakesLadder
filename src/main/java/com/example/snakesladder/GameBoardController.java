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
import java.util.concurrent.TimeUnit;

public class GameBoardController implements Initializable {
    private int diceRolledFinal;
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
                    System.out.println("Player 1 is on the move "+diceRolledFinal);
                    if(player1.isActive()){
                        movePlayer(player1);
                    }
                    else{
                        if (diceRolledFinal==1){
                            player1.setActive(true);
                            blueToken.setLayoutY(player1.getyCord());
                        }
                    }
                    player1.setTurn(false);
                    player2.setTurn(true);
                }
                else{
                    System.out.println("Player 2 is on the move" + diceRolledFinal);
                    if(player2.isActive()){
                        movePlayer(player2);
                    }
                    else{
                        if (diceRolledFinal==1){
                            System.out.println("Player 2 got one on the dice");
                            player2.setActive(true);
                            greenToken.setLayoutY(player2.getyCord());
                            player2.setTurn(false);
                            player1.setTurn(true);
                        }
                    }
                    player2.setTurn(false);
                    player1.setTurn(true);
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

    public void movePlayer(Player player){
        if(player.getColor().equals("blueToken")){
            int stepX=32;
            int stepY=43;
            for(int i=1;i<=diceRolledFinal;i++){
//                if(player.getTileNumber()%10!=1 && player.getTileNumber()>10){
//                    stepX=-stepX;
//                    blueToken.setLayoutY(stepY+blueToken.getLayoutY());
//                }
                blueToken.setLayoutX(stepX+blueToken.getLayoutX());
                player.setTileNumber(player.getTileNumber()+1);
            }
        }
        else{
            int stepX=32;
            int stepY=43;
            for(int i=1;i<=diceRolledFinal;i++){
                System.out.println(player.getTileNumber());
//                if(player.getTileNumber()%10!=1 && player.getTileNumber()>10){
//                    stepX=-stepX;
//                    greenToken.setLayoutY(stepY+greenToken.getLayoutY());
//                }
                greenToken.setLayoutX(stepX+greenToken.getLayoutX());
                player.setTileNumber(player.getTileNumber()+1);
            }
        }
    }
}
