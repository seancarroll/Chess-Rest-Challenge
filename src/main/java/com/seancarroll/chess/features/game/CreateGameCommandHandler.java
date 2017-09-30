package com.seancarroll.chess.features.game;

import javax.inject.Named;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.domain.Player;
import com.seancarroll.chess.infrastructure.messaging.RequestHandler;
import com.seancarroll.chess.services.GameService;

@Named
public class CreateGameCommandHandler implements RequestHandler<CreateGameCommand, Game>{

    private final GameService gameService;
    
    public CreateGameCommandHandler(GameService gameService) {
        this.gameService = gameService;
    }
    
    @Override
    public Game handle(CreateGameCommand command) {
        Game game = new Game(
                command.getEvent(), 
                command.getSite(), 
                new Player(command.getWhitePlayer()), 
                new Player(command.getBlackPlayer()));
        gameService.save(game);
        return game;
    }

}
