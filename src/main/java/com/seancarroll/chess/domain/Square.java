package com.seancarroll.chess.domain;

public class Square {

    // TODO: I would like to use the standard language of file and rank but for now just use x and y
    //private final int x;
    //private final int y;
    private final Position position;
    private Piece piece;
    
    public Square(Position position) {
        this.position = position;
    }
    
    public Square(int x, int y) {
        this.position = new Position(x, y);
    }

//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
    
    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public void removePiece(Piece piece) {
        this.piece = null;
    }
    
    public boolean isOccupied() {
        return piece != null;
    }
    
    // TODO: override equals and hashcode
}
