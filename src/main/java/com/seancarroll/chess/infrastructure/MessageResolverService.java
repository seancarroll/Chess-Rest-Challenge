package com.seancarroll.chess.infrastructure;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.ObjectError;

public class MessageResolverService {

    private final MessageSource messageSource;
    
    public MessageResolverService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    public String resolveMessage(MessageSourceResolvable resolvable) {
        return messageSource.getMessage(resolvable, getCurrentLocale());
    }
    
    public String resolveMessage(String message) {
        return resolveMessage(message, null, null);
    }
    
    public String resolveMessage(String message, Object[] args, String defaultMessage) {
        return messageSource.getMessage(message, args, defaultMessage, getCurrentLocale());
    }
    
    public String resolveMessage(ObjectError objectError) {
        return messageSource.getMessage(objectError, getCurrentLocale());
    }
       
    private static Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }
}
