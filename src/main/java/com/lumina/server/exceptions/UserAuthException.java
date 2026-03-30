package com.lumina.server.exceptions;

public class UserAuthException extends RuntimeException{

    public UserAuthException(String message, Throwable cause) {

        super(message, cause);
    }
}

