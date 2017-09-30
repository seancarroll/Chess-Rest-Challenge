package com.seancarroll.chess.features.game;

import javax.inject.Named;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.exceptions.Guard;
import com.seancarroll.chess.infrastructure.messaging.RequestHandler;
import com.seancarroll.chess.services.GameService;

@Named
public class ResignCommandHandler implements RequestHandler<ResignCommand, Game> {

    private final GameService gameService;
    
    public ResignCommandHandler(GameService gameService) {
        this.gameService = gameService;
    }
    
    @Override
    public Game handle(ResignCommand request) {
        Game game = gameService.getGame(request.getGameId());
        Guard.checkResourceNotFound(game, "Could not find game " + request.getGameId());
        
        game.resign(request.getPlayer());
        return game;
    }

}
