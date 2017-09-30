package com.seancarroll.chess.infrastructure.messaging;

/**
 * 
 *
 */
public interface RequestHandlerProvider {
    
    /**
     * 
     * @param request
     * @return
     */
    public RequestHandler<Request, ?> getRequestHandler(Request request);
    

}