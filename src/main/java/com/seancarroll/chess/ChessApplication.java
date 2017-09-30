package com.seancarroll.chess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.seancarroll.chess.infrastructure.messaging.RequestDispatcher;
import com.seancarroll.chess.infrastructure.messaging.RequestDispatcherImpl;
import com.seancarroll.chess.infrastructure.messaging.RequestHandlerProvider;
import com.seancarroll.chess.infrastructure.messaging.RequestHandlerProviderImpl;
import com.seancarroll.chess.infrastructure.messaging.interceptors.LoggingRequestInterceptor;
import com.seancarroll.chess.infrastructure.messaging.interceptors.PostRequestHandler;
import com.seancarroll.chess.infrastructure.messaging.interceptors.PreRequestHandler;

@SpringBootApplication
public class ChessApplication {

    @Inject
    private  ConfigurableListableBeanFactory beanFactory;
    
	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}
	
	@Bean
	public RequestDispatcher requestDispatcher() {
	    RequestDispatcherImpl requestDispatcher = new RequestDispatcherImpl(requestHandlerProvider());
	    requestDispatcher.setPreRequestHandlers(getPreRequestHandlers());
	    requestDispatcher.setPostRequestHandlers(getPostRequestHandlers());
	    return requestDispatcher;
	}
	
	@Bean
	public RequestHandlerProvider requestHandlerProvider() {
	    return new RequestHandlerProviderImpl(beanFactory);
	}
	
	@Bean
	public List<PreRequestHandler> getPreRequestHandlers() {
	    List<PreRequestHandler> handlers = new ArrayList<>(1);
	    handlers.add(new LoggingRequestInterceptor());
	    return handlers;
	}
	
    @Bean
    public List<PostRequestHandler> getPostRequestHandlers() {
        List<PostRequestHandler> handlers = new ArrayList<>(1);
        handlers.add(new LoggingRequestInterceptor());
        return handlers;
    }
    
    
//    @Bean
//    @Primary
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        System.out.println("Config is starting.");
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        return objectMapper;
//    }
	
}
