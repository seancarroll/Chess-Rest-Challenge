package com.seancarroll.chess.infrastructure.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.seancarroll.chess.domain.ErrorDTO;
import com.seancarroll.chess.domain.ErrorResponse;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultExceptionHandler implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleError(Exception exception) {
        logger.error("Internal server error encountered", exception);
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "api_error");
        ErrorDTO error = new ErrorDTO("system_error", "System Error occurred");
        errorResponse.addError(error);
        
        return errorResponse;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
    
    
}
