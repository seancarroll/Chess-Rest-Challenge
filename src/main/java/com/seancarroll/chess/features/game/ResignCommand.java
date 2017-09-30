package com.seancarroll.chess.features.game;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.seancarroll.chess.infrastructure.messaging.Command;

public class ResignCommand implements Command {

    @NotNull
    private UUID gameId;
    
    @NotBlank
    private String player;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameId", getGameId())
                .add("player", getPlayer())
                .toString();
    }
    
}
