package com.seancarroll.chess.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private final String name;
    
    public Player(String name) {
        this.name = name;
    }
    
    @JsonCreator
    public static Player createPlayer(@JsonProperty("name") String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }
    
    
}
