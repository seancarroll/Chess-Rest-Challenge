package com.seancarroll.chess.features.game;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seancarroll.chess.Pgn;
import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.infrastructure.messaging.RequestDispatcher;

@RestController
@RequestMapping("/api/chess/game")
public class ChessGameController {

    private final RequestDispatcher dispatcher;
    
    public ChessGameController(RequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    @PostMapping
    public Game createGame(@Valid CreateGameCommand command) {
        return dispatcher.send(command);
    }
    
    @GetMapping(value = "/{gameId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Game getGameJson(@Valid GetGameQuery command) {
        return dispatcher.send(command);
    }
    
    @GetMapping(value = "/{gameId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getGamePgn(@Valid GetGameQuery command) {
        Game game = dispatcher.send(command);
        return Pgn.exportGame(game);
    }
    
    
    @PostMapping("/{gameId}/move")
    public Game move(@Valid MoveCommand command) {
        return dispatcher.send(command);
    }
    
    @PostMapping("/{gameId}/resign")
    public Game resign(@Valid ResignCommand command) {
        return dispatcher.send(command);
    }
    
    
}
