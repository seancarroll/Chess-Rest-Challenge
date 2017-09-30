package com.seancarroll.chess.domain;

import com.google.common.base.MoreObjects;

public class Move {

    private final String pgnMovetext;
    
    public Move(String pgnMovetext) {
        this.pgnMovetext = pgnMovetext;
    }

    public String getPgnMovetext() {
        return pgnMovetext;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pgnMovetext", getPgnMovetext())
                .toString();
    }
    
}
