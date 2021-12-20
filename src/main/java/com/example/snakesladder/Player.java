package com.example.snakesladder;

import javafx.scene.image.Image;

import java.util.Objects;

public class Player {
    private String color;
    private double xCord;
    private double yCord=488;
    private boolean turn=true;
    private int tileNumber=0;
    private double stepX=32;
    private double stepY=-45.55;
    private Image image_active,image_inactive;
    private boolean active=false;
    public Player(String color) {
        if(color.equals("blueToken")) {
            this.xCord = 38;
            this.image_inactive= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player1_inactive.jpg")));
            this.image_active= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player1_active.jpg")));

        }
        else {
            this.xCord = 52;
            this.image_inactive= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player2_inactive.jpg")));
            this.image_active= new Image(Objects.requireNonNull(getClass().getResourceAsStream("player2_active.jpg")));
        }
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
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
        this.yCord=448;
    }

    public double getStepX() {
        return stepX;
    }

    public void setStepX(double stepX) {
        this.stepX = stepX;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepY(double stepY) {
        this.stepY = stepY;
    }

    public Image getImage_active() {
        return image_active;
    }

    public void setImage_active(Image image_active) {
        this.image_active = image_active;
    }

    public Image getImage_inactive() {
        return image_inactive;
    }

    public void setImage_inactive(Image image_inactive) {
        this.image_inactive = image_inactive;
    }
}
