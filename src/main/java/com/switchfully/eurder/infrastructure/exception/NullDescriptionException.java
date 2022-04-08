package com.switchfully.eurder.infrastructure.exception;

public class NullDescriptionException extends RuntimeException {

    public NullDescriptionException() {
        super("Please provide a description!");
    }
}
