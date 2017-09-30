package com.seancarroll.chess.exceptions;

public class Guard {

    private Guard() {
        // static utility
    }
    
    public static void checkResourceNotFound(Object o, String message) {
        if (o == null) {
            throw new ResourceNotFoundException(message);
        }
    }
}
