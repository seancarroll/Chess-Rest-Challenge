package com.seancarroll.chess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wraps Not Found HTTP status code 404
 * @author seancarroll
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2547517196718044026L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
