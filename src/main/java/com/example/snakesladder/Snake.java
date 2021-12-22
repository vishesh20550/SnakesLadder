package com.example.snakesladder;

public class Snake {
    private int src;
    private int dest;

    public Snake(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
