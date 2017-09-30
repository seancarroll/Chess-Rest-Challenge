package com.seancarroll.chess.infrastructure.messaging;

/**
 * Defines a handler for a request
 * @param <TRequest> The type of message to be handled
 * @param <TResponse> The type of response to be returned by the handler
 */
public interface RequestHandler<TRequest extends Request, TResponse> {

    /**
     * Handles a request
     * @param request message to be handled
     * @return Response from the request
     */
    TResponse handle(TRequest request);
}