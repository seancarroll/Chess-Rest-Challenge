package com.seancarroll.chess.services;

import java.util.UUID;

import com.seancarroll.chess.domain.Game;

public interface GameService {

    void save(Game game);
    Game getGame(UUID id);
}
