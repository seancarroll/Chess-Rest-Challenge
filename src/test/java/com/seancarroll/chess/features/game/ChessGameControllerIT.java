package com.seancarroll.chess.features.game;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ChessGameControllerIT {

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void shouldBeAbleToCreateGameSuccessfully() throws Exception {
        MvcResult result = mvc.perform(post("/api/chess/game")
                .param("event", "Test Event")
                .param("site", "Test Site")
                .param("whitePlayer", "Sean")
                .param("blackPlayer", "Mike"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();
        //Map<String, Object> model = result.getModelAndView().getModel();
        //assertTrue(model.containsKey("bindingResults"));
        //BindingResult results = (BindingResult) model.get("bindingResults");
        //assertEquals(2, results.getErrorCount());
    }
    


}
