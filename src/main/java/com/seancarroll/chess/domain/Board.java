package com.seancarroll.chess.domain;

public class Board {

    private Square[][] squares = new Square[8][8];
    
    public Board() {
        // TODO: initialize board
    }
    
    public Square[][] getSquares() {
        return squares;
    }
}
