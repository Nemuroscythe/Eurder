package com.switchfully.eurder.infrastructure.exception;

public class NullNameException extends RuntimeException {

    public NullNameException() {
        super("Please provide a name!");
    }
}
