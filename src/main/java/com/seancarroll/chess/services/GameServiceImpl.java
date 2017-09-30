package com.seancarroll.chess.services;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Named;

import com.seancarroll.chess.domain.Game;

@Named
public class GameServiceImpl implements GameService {

    private Map<UUID, Game> store = new ConcurrentHashMap<>();
    
    @Override
    public void save(Game game) {
        store.put(game.getId(), game);
    }
    
    @Override
    public Game getGame(UUID id) {
        return store.get(id);
    }

}
