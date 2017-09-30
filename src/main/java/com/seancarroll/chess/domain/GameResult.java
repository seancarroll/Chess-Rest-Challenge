package com.seancarroll.chess.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GameResult {

    WHITE_WON("1-0"),
    BLACK_WON("0-1"),
    DRAW("1/2-1/2"),
    ONGOING("*");
    
    private final String result;
    
    GameResult(String result) {
        this.result = result;
    }
    
    @JsonValue
    @Override
    public String toString() {
        return result;
    }
}
