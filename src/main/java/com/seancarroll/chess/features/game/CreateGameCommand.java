package com.seancarroll.chess.features.game;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.seancarroll.chess.infrastructure.messaging.Command;

public class CreateGameCommand implements Command {

    @NotBlank
    private String event;
    
    @NotBlank
    private String site;
    
    @NotBlank
    private String whitePlayer;
    
    @NotBlank
    private String blackPlayer;
    
    public String getEvent() {
        return event;
    }
    
    public void setEvent(String event) {
        this.event = event;
    }
    
    public String getSite() {
        return site;
    }
    
    public void setSite(String site) {
        this.site = site;
    }
    
    public String getWhitePlayer() {
        return whitePlayer;
    }
    
    public void setWhitePlayer(String whitePlayer) {
        this.whitePlayer = whitePlayer;
    }
    
    public String getBlackPlayer() {
        return blackPlayer;
    }
    
    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("event", getEvent())
                .add("site", getSite())
                .add("whitePlayer", getWhitePlayer())
                .add("blackPlayer", getBlackPlayer())
                .toString();
    }
    
    
}
