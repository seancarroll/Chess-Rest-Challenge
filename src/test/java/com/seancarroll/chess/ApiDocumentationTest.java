package com.seancarroll.chess;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seancarroll.chess.domain.Game;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ChessApplication.class })
public class ApiDocumentationTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Inject
    private WebApplicationContext context;
    
    @Inject
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }
    
    @Test
    public void createGameExample() throws Exception {
        mockMvc.perform(post("/api/chess/game")
                .param("event", "Test Event")
                .param("site", "Test Site")
                .param("whitePlayer", "Sean")
                .param("blackPlayer", "Mike"))
                .andDo(document("create-game",
                        requestParameters(
                                parameterWithName("event").description("The name of the tournament or match event"),
                                parameterWithName("site").description("The location of the event"),
                                parameterWithName("whitePlayer").description("The player of the white pieces"),
                                parameterWithName("blackPlayer").description("The player of the black pieces")),
                        responseFields(
                                fieldWithPath("id").description("Game id"),
                                fieldWithPath("event").description("The name of the tournament or match event"),
                                fieldWithPath("site").description("The location of the event"),
                                fieldWithPath("date").description("The starting date of the game"),
                                fieldWithPath("whitePlayer").description("The player of the white pieces"),
                                fieldWithPath("blackPlayer").description("The player of the black pieces"),
                                fieldWithPath("round").description("The playing round ordinal of the game"),
                                fieldWithPath("result").description("The result of the game"),
                                fieldWithPath("board").description("The current board"),
                                fieldWithPath("moves").description("The moves that occurred during the game"))));
    }

    @Test
    public void resignGameExample() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(post("/api/chess/game/{gameId}/resign", game.getId())
                .param("player", game.getWhitePlayer().getName()))
            .andDo(document("resign-game",
                    pathParameters(
                            parameterWithName("gameId").description("The id of the game")),
                    requestParameters(
                            parameterWithName("player").description("The player who is resigning")),
                    responseFields(
                            fieldWithPath("id").description("The id of the game"),
                            fieldWithPath("event").description("The name of the tournament or match event"),
                            fieldWithPath("site").description("The location of the event"),
                            fieldWithPath("date").description("The starting date of the game"),
                            fieldWithPath("whitePlayer").description("The player of the white pieces"),
                            fieldWithPath("round").description("The playing round ordinal of the game"),
                            fieldWithPath("blackPlayer").description("The player of the black pieces"),
                            fieldWithPath("result").description("The result of the game"),
                            fieldWithPath("board").description("The current board"),
                            fieldWithPath("moves").description("The moves that occurred during the game"))));
    }
    
    @Test
    public void moveExample() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(post("/api/chess/game/{gameId}/move", game.getId())
                .param("san", "e4"))
            .andDo(document("move",
                    pathParameters(
                            parameterWithName("gameId").description("The id of the game")),
                    requestParameters(
                            parameterWithName("san").description("The players move in SAN (Standard Algebraic Notation)")),
                    responseFields(
                            fieldWithPath("id").description("Game id"),
                            fieldWithPath("event").description("The name of the tournament or match event"),
                            fieldWithPath("site").description("The location of the event"),
                            fieldWithPath("date").description("The starting date of the game"),
                            fieldWithPath("whitePlayer").description("The player of the white pieces"),
                            fieldWithPath("blackPlayer").description("The player of the black pieces"),
                            fieldWithPath("round").description("The playing round ordinal of the game"),             
                            fieldWithPath("result").description("The result of the game"),
                            fieldWithPath("board").description("The current board"),
                            fieldWithPath("moves").description("The moves that occurred during the game"))));
    }
    
    @Test
    public void getGameExample() throws Exception {
        MvcResult createGamePostResult = mockMvc.perform(IntegrationTestHelper.createGamePostMockHttpServietRequestBuilder()).andReturn();
        
        Game game = mapper.readValue(createGamePostResult.getResponse().getContentAsString(), Game.class);
        
        mockMvc.perform(get("/api/chess/game/{gameId}", game.getId()))
            .andDo(document("get-game",
                    pathParameters(
                            parameterWithName("gameId").description("The id of the game")),
                    responseFields(
                            fieldWithPath("id").description("Game id"),
                            fieldWithPath("event").description("The name of the tournament or match event"),
                            fieldWithPath("site").description("The location of the event"),
                            fieldWithPath("date").description("The starting date of the game"),
                            fieldWithPath("whitePlayer").description("The player of the white pieces"),
                            fieldWithPath("blackPlayer").description("The player of the black pieces"),
                            fieldWithPath("round").description("The playing round ordinal of the game"),             
                            fieldWithPath("result").description("The result of the game"),
                            fieldWithPath("board").description("The current board"),
                            fieldWithPath("moves").description("The moves that occurred during the game"))));

    }
}
