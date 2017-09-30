package com.seancarroll.chess.features.game;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;
import com.seancarroll.chess.infrastructure.messaging.Request;

public class GetGameQuery implements Request {

    @NotNull
    private UUID gameId;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameId", getGameId())
                .toString();
    }
    
    
}
