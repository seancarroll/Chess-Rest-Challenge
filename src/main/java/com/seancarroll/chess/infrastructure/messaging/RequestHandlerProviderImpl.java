package com.seancarroll.chess.infrastructure.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.seancarroll.chess.utils.ReflectionUtils;

/**
 * Default implementation that looks into the application event listener 
 * context refreshed event and finds all beans implements IRequestHandler
 *
 */
public class RequestHandlerProviderImpl implements RequestHandlerProvider,
    ApplicationListener<ContextRefreshedEvent> {

    private ConfigurableListableBeanFactory beanFactory;
    private Map<Class<?>, RequestHandler<Request, ?>> handlers = new HashMap<Class<?>, RequestHandler<Request, ?>>();
    
    @Inject
    public RequestHandlerProviderImpl(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    
    public RequestHandler<Request, ?> getRequestHandler(Request request) {
        RequestHandler<Request, ?> handler = handlers.get(request.getClass());
        if (handler == null) {
            throw new NoHandlerForRequestException("request handler not found for class " + request.getClass());
        }
        return handler;
    }

    /**
     * 
     * @param event
     */
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] requestHandlersNames = beanFactory.getBeanNamesForType(RequestHandler.class);
        for (String beanName : requestHandlersNames) {
            BeanDefinition requestHandler = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> handlerClass = Class.forName(requestHandler.getBeanClassName());
                Class<?> requestClass = ReflectionUtils.getTypeArgumentForGenericInterface(handlerClass, RequestHandler.class);
                RequestHandler<Request, ?> handler = beanFactory.getBean(beanName, RequestHandler.class);
                handlers.put(requestClass, handler);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}