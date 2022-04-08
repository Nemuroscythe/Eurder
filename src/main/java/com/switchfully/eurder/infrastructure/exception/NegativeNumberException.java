package com.switchfully.eurder.infrastructure.exception;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException() {
        super("Please provide a positive number!");
    }
}
