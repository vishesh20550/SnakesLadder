package com.example.snakesladder;

import javafx.scene.image.ImageView;

public class Player {
    private String color;
    private int xCord;
    private int yCord=488;
    private boolean turn=true;
    private int tileNumber=0;


    private boolean active=false;

    public Player(String color) {
        if(color.equals("blueToken"))
            this.xCord=38;
        else
            this.xCord=52;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
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
}
