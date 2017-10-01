package com.seancarroll.chess.domain;

import java.util.Collection;

public class Knight extends Piece {

    private final PieceColor color;
    private Position position;
    
    public Knight(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }
    
    @Override
    public PieceColor getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
    
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Position position) {
        this.position = position;
    }

    @Override
    public Collection<Square> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

}
