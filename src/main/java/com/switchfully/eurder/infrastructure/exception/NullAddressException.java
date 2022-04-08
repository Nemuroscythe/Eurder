package com.switchfully.eurder.infrastructure.exception;

public class NullAddressException extends RuntimeException {

    public NullAddressException() {
        super("Please provide an address!");
    }
}
