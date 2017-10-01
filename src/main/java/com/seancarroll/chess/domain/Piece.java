package com.seancarroll.chess.domain;

import java.util.Collection;

public abstract class Piece {

    // should piece know whether its been captured/available?
    
    public abstract PieceColor getColor();
    public abstract PieceType getType();
    
    // TODO: Should piece really know about its position?
    // Initial thought is that position should be tracked by board and pieces should not handle move but for this exercise lets do it
    public abstract Position getPosition();
    public abstract void move(Position position);
    public abstract Collection<Square> getPossibleMoves();
    
}
