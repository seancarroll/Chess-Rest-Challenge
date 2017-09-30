package com.seancarroll.chess.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Game {

    private final UUID id;
    private final String event;
    private final String site;
    private final ZonedDateTime date;
    private int round;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private GameResult result;
    private final List<Move> moves = new ArrayList<>();
    
    public Game(String event, String site, Player white, Player black) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.site = site;
        this.date = ZonedDateTime.now();
        this.round = 0;
        this.whitePlayer = white;
        this.blackPlayer = black;
        this.result = GameResult.ONGOING;
    }

    public UUID getId() {
        return id;
    }
    
    public String getEvent() {
        return event;
    }

    public String getSite() {
        return site;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public int getRound() {
        return round;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public GameResult getResult() {
        return result;
    }
    
    public List<Move> getMoves() {
        return moves;
    }
    
    public void addMove(String move) {
        moves.add(new Move(move));
    }
    
    public void resign(String player) {
        Preconditions.checkNotNull(player);
        Preconditions.checkState(result == GameResult.ONGOING);
        if (Objects.equal(player, whitePlayer.getName())) {
           result = GameResult.BLACK_WON;
        } else if (Objects.equal(player, blackPlayer.getName())) {
            result = GameResult.WHITE_WON;
        } else {
            throw new IllegalArgumentException("Player " + player + " is not a valid player.");
        }
    }
    
}
