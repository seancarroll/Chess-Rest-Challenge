package com.seancarroll.chess.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final PieceColor color;
    private Position position;
    
    public Pawn(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }
    
    @Override
    public PieceColor getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
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
        int dy = color == PieceColor.WHITE ? 1 : -1;
        possibleMoves.add(new Square(position.getX(), position.getY() + dy));
        
        // double forward if at origin
        int baseRow = color == PieceColor.WHITE ? 0 : 7;
        if (position.getY() == baseRow) {
            possibleMoves.add(new Square(position.getX(), position.getY() + dy * 2));
        }
        
        // attacks
        possibleMoves.add(new Square(position.getX() + 1, position.getY() + dy));
        possibleMoves.add(new Square(position.getX() - 1, position.getY() + dy));
        
        return possibleMoves;
    }

}
