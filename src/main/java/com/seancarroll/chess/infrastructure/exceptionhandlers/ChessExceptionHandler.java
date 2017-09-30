package com.seancarroll.chess.infrastructure.exceptionhandlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

import com.seancarroll.chess.domain.ErrorDTO;
import com.seancarroll.chess.domain.ErrorResponse;
import com.seancarroll.chess.infrastructure.MessageResolverService;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChessExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChessExceptionHandler.class);
    private final MessageResolverService messageResolver;
    
    public ChessExceptionHandler(MessageSource messageSource) {
        this.messageResolver = new MessageResolverService(messageSource);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        logger.error("Illegal argument exception occurred", exception);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, "invalid_request");
        ErrorDTO e = new ErrorDTO();
        e.setMessage(exception.getMessage());
        response.addError(e);
        return response;
    }
    
    
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalStateException(IllegalStateException exception) {
        logger.error("Illegal argument exception occurred", exception);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, "invalid_request");
        ErrorDTO e = new ErrorDTO();
        e.setMessage(exception.getMessage());
        response.addError(e);
        return response;
    }
    
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBindException(BindException bindException, HandlerMethod handler, 
            HttpServletRequest request, HttpServletResponse response) {
        logger.error("bad request bind exception", bindException.getBindingResult());
        return processErrors(bindException.getBindingResult());
    }
    
    private ErrorResponse processErrors(Errors validationErrors) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, "invaild_request");
        
        for (ObjectError error : validationErrors.getAllErrors()) {
            ErrorDTO e = new ErrorDTO();
            e.setCode(error.getCode());
            e.setMessage(messageResolver.resolveMessage(error));
            
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                e.setField(fieldError.getField());
                if (fieldError.getRejectedValue() != null) {
                    e.setRejectedValue(String.valueOf(fieldError.getRejectedValue()));
                }
            }
            response.addError(e);
        }
        
        return response;
    }
}
