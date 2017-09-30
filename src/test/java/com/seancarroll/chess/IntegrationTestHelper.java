package com.seancarroll.chess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class IntegrationTestHelper {

    private IntegrationTestHelper() {
        // static utility
    }
    
    
    public static MockHttpServletRequestBuilder createGamePostMockHttpServietRequestBuilder() {
        return post("/api/chess/game")
        .param("event", "Test Event")
        .param("site", "Test Site")
        .param("whitePlayer", "Sean")
        .param("blackPlayer", "Mike");
    }
}
