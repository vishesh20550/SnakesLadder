package com.example.snakesladder;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {
    private int diceRolledFinal;
    private boolean flag=false;
    HashMap<Integer,Snake> snakes = new HashMap<>();
    HashMap<Integer,Ladder> ladders= new HashMap<>();

    private MediaPlayer player ;


    @FXML
    private ImageView exitImageView;
    @FXML
    private ImageView returnImageView;
    @FXML
    private ImageView p1ImageView;
    @FXML
    private ImageView p2ImageView;
    @FXML
    private ImageView win;
    @FXML
    private ImageView menuImageView;
    @FXML
    private ImageView replayImageView;
    @FXML
    private ImageView blueToken;
    @FXML
    private ImageView greenToken;
    @FXML
    private ImageView backImageView;
    @FXML
    private ImageView diceImageView;
    @FXML
    private ImageView boardImageView;
    @FXML
    private ImageView diceArrow;
    @FXML
    private ImageView startArrow;
    Player player1= new Player("blueToken");
    Player player2= new Player("greenToken");

    // MEDIA PLAYER
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

    @FXML
    private void onBackClick(MouseEvent event) throws IOException {
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/Button.mp3").toUri().toString());
        play(media);
        setOpacityGameBoard(0.5);
        win.setVisible(true);
        win.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("end_game.png"))));
        returnImageView.setVisible(true);
        exitImageView.setVisible(true);
        returnImageView.setOnMouseClicked(mouseEvent -> {
            play(media);
            setOpacityGameBoard(1);
            returnImageView.setVisible(false);
            exitImageView.setVisible(false);
            win.setVisible(false);
        });
        exitImageView.setOnMouseClicked(mouseEvent -> {
            play(media);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
            new IntroController().setAndShowScene(event, fxmlLoader);
        });
    }

    @FXML
    private void onDiceRoll(MouseEvent event) {
        if(!flag){
            flag=true;
            Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/newDice.mp3").toUri().toString());
            play(media);
            diceArrow.setVisible(false);
            Random rand = new Random();
            Thread thread = new Thread() {
                int diceRolled;
                public void run() {
                    diceRolled = (rand.nextInt(6) + 1);
                    for (int i = 2; i < 8; i++) {
                        File file = new File("src/main/resources/com/example/snakesladder/roll" + i + ".jpg");
                        diceImageView.setImage(new Image(file.toURI().toString()));
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    File file = new File("src/main/resources/com/example/snakesladder/dice" + diceRolled + ".png");
                    diceImageView.setImage(new Image(file.toURI().toString()));
                    diceRolledFinal = diceRolled;
                    if (player1.isTurn()) {
                        try {
                            checkPlayerStatus(player1, blueToken, player2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        p1ImageView.setImage(player1.getImage_inactive());
                        p2ImageView.setImage(player2.getImage_active());
                    } else {
                        try {
                            checkPlayerStatus(player2, greenToken, player1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        p1ImageView.setImage(player1.getImage_active());
                        p2ImageView.setImage(player2.getImage_inactive());
                    }
                    diceArrow.setVisible(true);
                    flag=false;
                }
            };
            thread.start();
        }
    }

    private void checkPlayerStatus(Player player2, ImageView token, Player player1) throws IOException {
        if(player2.isActive()){
            movePlayer(player2);
        }
        else{
            if (diceRolledFinal==1){
                Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/unlock.mp3").toUri().toString());
                play(media);
                player2.setActive(true);
//                greenToken.setLayoutY(player2.getyCord());
                TranslateTransition transition= new TranslateTransition();
                transition.setByY(-44);
                transition.setDuration(Duration.millis(100));
                transition.setNode(token);
                transition.setOnFinished(event -> {
                    player2.setTileNumber(1);
                    System.out.println(player2.getColor() + " is on " + player2.getTileNumber());
                });
                transition.play();
            }
        }
        player2.setTurn(false);
        player1.setTurn(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        diceArrow.setVisible(true);
        initializeSnakesAndLadders();
        p1ImageView.setImage(player1.getImage_active());
        p2ImageView.setImage(player2.getImage_inactive());
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(diceArrow);
        translate.setDuration(Duration.millis(400));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(15);
        translate.setByY(-15);
        translate.setAutoReverse(true);
        translate.play();
    }

    private void movePlayer(Player player) {
        if(player.getTileNumber()+diceRolledFinal>100){
            return;
        }
        ImageView token;
        if(player.getColor().equals("blueToken"))
            token=blueToken;
        else
            token=greenToken;
        Thread thread = new Thread(() -> {
            diceImageView.setDisable(true);
            for (int i=0;i<diceRolledFinal;i++){
                TranslateTransition t=movePlayerHelper(player,token);
                t.play();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(player.getTileNumber()==100){
                onWin(player);
            }
            if (snakes.containsKey(player.getTileNumber())) {
                snakePath(player.getTileNumber(), token, player);
            }
            else if (ladders.containsKey(player.getTileNumber())) {
               ladderPath(player.getTileNumber(), token, player);
            }
            diceImageView.setDisable(false);

        });
        thread.start();
    }

    private TranslateTransition movePlayerHelper(Player player,ImageView token){
        TranslateTransition transition= new TranslateTransition();
        transition.setNode(token);
        double byX,byY;
        if(((player.getTileNumber()+1)%10==1 )&& (player.getTileNumber()>=10)){
            player.setStepX(-player.getStepX());
            byX=0;
            byY=player.getStepY();
        }
        else{
            byX=player.getStepX();
            byY=0;
        }
        player.setTileNumber(player.getTileNumber()+1);
        transition.setByX(byX);
        transition.setByY(byY);
        transition.setDuration(Duration.millis(200));
        transition.setOnFinished(event -> transition.stop());
        return transition;
    }

    private void ladderPath(int src,ImageView token,Player player){
        player.setTileNumber(ladders.get(player.getTileNumber()).getDest());
        double byX,byY;
        if(src==64 || src==66 || src==68){
            byX=-32;
            byY=-(2*45.55);
        }
        else if(src==37 || src == 35 || src==33 || src ==5 || src == 7  || src == 9){
            player.setStepX(-player.getStepX());
            byX=-32;
            byY=-(45.55);
        }
        else{
//            player.setStepX(-player.getStepX());
            byX=0;
            byY=-(4*45.55);
        }
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/Ladder.mp3").toUri().toString());
        play(media);
        translateSnakeLadder(token,byX,byY);
    }

    private void snakePath(int src,ImageView token,Player player){
        player.setTileNumber(snakes.get(player.getTileNumber()).getDest());
        double byX,byY;
        if(src==24 || src==26 || src==28){
            player.setStepX(-player.getStepX());
            byX=-32;
            byY=45.55;
        }
        else if(src==59 || src == 57 || src==55 || src ==99 || src == 97  || src == 95){
            byX=32;
            byY=(2*45.55);
        }
        else{
            player.setStepX(-player.getStepX());
            byX=0;
            byY=(5*45.55);
        }
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/snake.mp3").toUri().toString());
        play(media);
        translateSnakeLadder(token,byX,byY);

    }

    private void translateSnakeLadder(ImageView token,double x, double y){
        TranslateTransition transition= new TranslateTransition();
        transition.setNode(token);
        transition.setByY(y);
        transition.setByX(x);
        transition.setDuration(Duration.millis(300));
        transition.play();

    }

    private void onWin(Player player) {
        Media media= new Media(Paths.get("src/main/resources/com/example/snakesladder/Victory.mp3").toUri().toString());
        play(media);

        setOpacityGameBoard(0.5);
        win.setVisible(true);
        if(player.getColor().equals("blueToken")){
            win.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("win_image_player1.png"))));
        }
        else{
            win.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("win_image_player2.png"))));
        }
        replayImageView.setVisible(true);
        menuImageView.setVisible(true);
        replayImageView.setOnMouseClicked(mouseEvent -> new IntroController().onPwmCLick(mouseEvent));
        menuImageView.setOnMouseClicked(mouseEvent -> {
            Media media1= new Media(Paths.get("src/main/resources/com/example/snakesladder/Button.mp3").toUri().toString());
            play(media1);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IntroPage.fxml"));
            new IntroController().setAndShowScene(mouseEvent, fxmlLoader);
        });
    }

    private void setOpacityGameBoard(double v){
        diceImageView.setOpacity(v);
        boardImageView.setOpacity(v);
        blueToken.setOpacity(v);
        greenToken.setOpacity(v);
        backImageView.setOpacity(v);
        diceArrow.setOpacity(0);
        startArrow.setOpacity(v);
        p1ImageView.setOpacity(v);
        p2ImageView.setOpacity(v);
    }

    private void initializeSnakesAndLadders(){
        snakes.put(24,new Snake(24,18));
        snakes.put(26,new Snake(26, 16));
        snakes.put(28,new Snake(28,14));
        snakes.put(55,new Snake(55,34));
        snakes.put(57,new Snake(57,36));
        snakes.put(59,new Snake(59,38));
        snakes.put(95,new Snake(95,74));
        snakes.put(97,new Snake(97,76));
        snakes.put(91,new Snake(91,50));
        snakes.put(99,new Snake(99,78));
        ladders.put(5,new Ladder(5,17));
        ladders.put(7,new Ladder(7,15));
        ladders.put(9,new Ladder(9,13));
        ladders.put(33,new Ladder(33,47));
        ladders.put(35,new Ladder(35,45));
        ladders.put(37,new Ladder(37,43));
        ladders.put(41,new Ladder(41,81));
        ladders.put(68,new Ladder(68,87));
        ladders.put(66,new Ladder(66,85));
        ladders.put(64,new Ladder(64,83));

    }
}
