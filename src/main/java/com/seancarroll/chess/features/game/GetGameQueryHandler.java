package com.seancarroll.chess.features.game;

import javax.inject.Named;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.exceptions.Guard;
import com.seancarroll.chess.infrastructure.messaging.RequestHandler;
import com.seancarroll.chess.services.GameService;

@Named
public class GetGameQueryHandler implements RequestHandler<GetGameQuery, Game> {

    private final GameService gameService;
    
    public GetGameQueryHandler(GameService gameService) {
        this.gameService = gameService;
    }
    
    @Override
    public Game handle(GetGameQuery request) {
        Game game = gameService.getGame(request.getGameId());
        Guard.checkResourceNotFound(game, "Could not find game " + request.getGameId());
        return game;
    }

}
