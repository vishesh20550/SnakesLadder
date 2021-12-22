package com.example.snakesladder;

import javafx.scene.image.Image;

import java.util.Objects;

public class Player {
    private final String color;
    private boolean turn=true;
    private int tileNumber=0;
    private double stepX=32;
    private final Image image_active;
    private final Image image_inactive;
    private boolean active=false;
    public Player(String color) {
        if(color.equals("blueToken")) {
            this.image_inactive= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player1_inactive.png")));
            this.image_active= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player1_active.png")));

        }
        else {
            this.image_inactive= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player2_inactive.png")));
            this.image_active= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player2_active.png")));
        }
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getStepX() {
        return stepX;
    }

    public void setStepX(double stepX) {
        this.stepX = stepX;
    }

    public double getStepY() {
        return -45.55;
    }

    public Image getImage_active() {
        return image_active;
    }

    public Image getImage_inactive() {
        return image_inactive;
    }

}
