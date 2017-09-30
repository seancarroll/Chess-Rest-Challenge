package com.seancarroll.chess.infrastructure.messaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.seancarroll.chess.infrastructure.messaging.interceptors.PostRequestHandler;
import com.seancarroll.chess.infrastructure.messaging.interceptors.PreRequestHandler;

/**
 * Implementation of the dispatcher (aka command bus) that dispatches requests
 * to the handlers subscribed to the specific type of request. Interceptors may 
 * be configured to add processing to requests regardless of their type
 *
 */
public class RequestDispatcherImpl implements RequestDispatcher {

    private RequestHandlerProvider requestHandlerProvider;
    private Iterable<? extends PreRequestHandler> preRequestHandlers = Collections.emptyList();
    private Iterable<? extends PostRequestHandler> postRequestHandlers = Collections.emptyList();
    
    /**
     * 
     * @param requestHandlerProvider
     * @param validator
     */
    public RequestDispatcherImpl(
            RequestHandlerProvider requestHandlerProvider) {
        this.requestHandlerProvider = requestHandlerProvider;
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public <TResponse> TResponse send(Request request) {
 
        for (PreRequestHandler preRequestHandler : preRequestHandlers) {
            preRequestHandler.handle(request);
        }
        
        RequestHandler<Request, ?> handler = requestHandlerProvider.getRequestHandler(request);
        TResponse response = (TResponse) handler.handle(request);
           
        for (PostRequestHandler postRequestHandler : postRequestHandlers) {
            postRequestHandler.handle(request, response);
        }
        
        return response;
    }
    
    /**
     * Registers the given list of pre-interceptors to the dispatcher. All incoming requests will pass through the
     * interceptors at the given order before the request is passed to the handler for processing.
     *
     * @param preRequestHandlers the interceptors to invoke prior to request being handled
     */
    public void setPreRequestHandlers(List<? extends PreRequestHandler> preRequestHandlers) {
        this.preRequestHandlers = new ArrayList<PreRequestHandler>(preRequestHandlers);
    }

    /**
     * Registers the given list of post-interceptors to the dispatcher. All incoming requests will pass through
     * the interceptors at the given order after the request is handled by the dispatcher
     *
     * @param postRequestHandlers The interceptors to invoke after the request has been handled
     */
    public void setPostRequestHandlers(List<? extends PostRequestHandler> postRequestHandlers) {
        this.postRequestHandlers = new ArrayList<PostRequestHandler>(postRequestHandlers);
    }
}
