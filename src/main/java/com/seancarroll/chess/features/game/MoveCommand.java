package com.seancarroll.chess.features.game;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.seancarroll.chess.infrastructure.messaging.Command;

public class MoveCommand implements Command {

    @NotNull
    private UUID gameId;
    
    @NotBlank
    private String san;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameId", getGameId())
                .add("san", getSan())
                .toString();
    }
    
    
    
}
