package com.seancarroll.chess.infrastructure.messaging.interceptors;

import com.seancarroll.chess.infrastructure.messaging.Request;

/**
 * Interceptors that allow requests to be intercepted before they are dispatched
 * by the dispatcher. 
 * <p>
 * I've thought about having this return IRequest<?> request so that preRequestHandlers
 * could alter the request message eg Adding currentUser to request.
 * @param <TRequest> The type of message to be handled 
 */
public interface PreRequestHandler {

    /**
     * Invoked each time a request is about to be handled by the dispatcher
     * @param request The request message to be dispatched to the dispatcher
     */
    void handle(Request request);
}
