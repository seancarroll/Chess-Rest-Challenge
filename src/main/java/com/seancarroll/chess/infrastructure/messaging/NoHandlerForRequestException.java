package com.seancarroll.chess.infrastructure.messaging;

/**
 * Exception indicating that no suitable handler could be found for the given request.
 *
 */
public class NoHandlerForRequestException extends RuntimeException {

    private static final long serialVersionUID = -5137199292058182778L;

    public NoHandlerForRequestException(String message) {
        super(message);
    }

}
