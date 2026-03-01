package com.lumina.server.exceptions;

public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message, Throwable cause) {

        super(message, cause);
    }
}
