package com.seancarroll.chess.features.game;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seancarroll.chess.IntegrationTestHelper;
import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.domain.GameResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ChessGameControllerIT {

    @Inject
    private MockMvc mockMvc;
    
    @Inject
    private ObjectMapper mapper;
    
    @Test
    public void shouldBeAbleToCreateGameSuccessfully() throws Exception {
        mockMvc.perform(post("/api/chess/game")
                .param("event", "Test Event")
                .param("site", "Test Site")
                .param("whitePlayer", "Sean")
                .param("blackPlayer", "Mike"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.event").value("Test Event"))
                .andExpect(jsonPath("$.site").value("Test Site"))
                .andExpect(jsonPath("$.result").value(GameResult.ONGOING.toString()))
                .andExpect(jsonPath("$.whitePlayer.name").value("Sean"))
                .andExpect(jsonPath("$.blackPlayer.name").value("Mike"));
    }
    
    @Test
    public void shouldBeAbleToResignOngoingGame() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(post("/api/chess/game/{gameId}/resign", game.getId())
                .param("player", game.getWhitePlayer().getName()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(GameResult.BLACK_WON.toString()));
        
    }
    
    @Test
    public void shouldNotBeAbleToResignTwice() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(post("/api/chess/game/{gameId}/resign", game.getId())
                .param("player", game.getWhitePlayer().getName()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(GameResult.BLACK_WON.toString()));
        
        mockMvc.perform(post("/api/chess/game/{gameId}/resign", game.getId())
                .param("player", game.getBlackPlayer().getName()))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.type").value("invalid_request"))
        .andExpect(jsonPath("$.errors.length()").value(1))
        .andExpect(jsonPath("$.errors[0].message").value("Cannot resign a game that not currently ongoing"));
    }
    
    @Test
    public void onlyValidPlayerShouldBeAbleToResign() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(post("/api/chess/game/{gameId}/resign", game.getId())
                .param("player", "Some Invalid Player"))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.type").value("invalid_request"))
        .andExpect(jsonPath("$.errors.length()").value(1))
        .andExpect(jsonPath("$.errors[0].message").value("Player Some Invalid Player is not a valid player."));
    }

}
