package com.seancarroll.chess.features.game;

import javax.inject.Named;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.exceptions.Guard;
import com.seancarroll.chess.infrastructure.messaging.RequestHandler;
import com.seancarroll.chess.services.GameService;

@Named
public class MoveCommandHandler implements RequestHandler<MoveCommand, Game> {

    private final GameService gameService;
    
    public MoveCommandHandler(GameService gameService) {
        this.gameService = gameService;
    }
    
    @Override
    public Game handle(MoveCommand command) {
        Game game = gameService.getGame(command.getGameId());
        Guard.checkResourceNotFound(game, "Could not find game " + command.getGameId());
        
        // TODO: need to validate move. Confirm that is a legal move
        
        game.addMove(command.getSan());
        
        return game;
    }

}
