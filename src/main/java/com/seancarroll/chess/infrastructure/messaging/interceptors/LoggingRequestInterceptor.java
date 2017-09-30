package com.seancarroll.chess.infrastructure.messaging.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seancarroll.chess.infrastructure.messaging.Request;

/**
 * Interceptor that logs the request prior to being dispatched to the dispatcher 
 * as well as the response after being dispatched.
 *
 * @param <TRequest> The type of message to be handled
 * @param <TResponse> The type of response to be returned by the handler
 */
public class LoggingRequestInterceptor implements PreRequestHandler, PostRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    
    public LoggingRequestInterceptor() {
    }

    public void handle(Request request) {
        logger.info("Incoming request [{}]", request);
    }
    
    public void handle(Request request, Object response) {
        logger.info("[{}] executed with response [{}]", request.getClass().getSimpleName(), response);
    }
}
