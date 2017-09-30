package com.seancarroll.chess.infrastructure.messaging.interceptors;

import com.seancarroll.chess.infrastructure.messaging.Request;

/**
 * Interceptors that allow requests to be intercepted after they are dispatched
 * by the dispatcher.
 *
 * @param <TRequest> The type of message to be handled
 */
public interface PostRequestHandler {
 
    /**
     * 
     * @param request
     * @param response
     */
    void handle(Request request, Object response);
    
}