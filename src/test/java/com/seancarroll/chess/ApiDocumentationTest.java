package com.seancarroll.chess;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ChessApplication.class })
public class ApiDocumentationTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Inject
    private WebApplicationContext context;

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
                        responseFields(
                                fieldWithPath("id").description("Game id"),
                                fieldWithPath("event").description("The name of the tournament or match event"),
                                fieldWithPath("site").description("The location of the event"),
                                fieldWithPath("date").description("The starting date of the game"),
                                fieldWithPath("whitePlayer").description("The player of the white pieces"),
                                fieldWithPath("blackPlayer").description("The player of the black pieces"),
                                fieldWithPath("round").description("The playing round ordinal of the game"),
                                fieldWithPath("result").description("The result of the game"))));
    }

}
