package com.seancarroll.chess.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private final PieceColor color;
    private Position position;
    
    public Rook(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }
    
    @Override
    public PieceColor getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
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
        List<Square> possibleMoves = new ArrayList<>();

        for (int x = 0; x <= 8; x++) {
            if (x != position.getX()) {
                possibleMoves.add(new Square(x, position.getY()));
            }
        }
        for (int y = 0; y <= 8; y++) {
            if (y != position.getY()) {
                possibleMoves.add(new Square(position.getX(), y));
            }
        }
        
        return possibleMoves;
    }

}
