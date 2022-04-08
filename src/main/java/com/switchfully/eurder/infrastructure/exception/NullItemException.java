package com.switchfully.eurder.infrastructure.exception;

public class NullItemException extends RuntimeException {

    public NullItemException() {
        super("Please provide an item!");
    }
}
