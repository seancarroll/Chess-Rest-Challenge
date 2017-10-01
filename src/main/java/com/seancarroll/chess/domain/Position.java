package com.seancarroll.chess.domain;

public class Position {

    // TODO: would be used to convert san to x, y coordinates
    // Would also like to use the rank and file terminology
    private static final String[] COL_NAMES = {"a", "b", "c", "d", "e", "f", "g", "h"};

    private int x;
    private int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
