package com.switchfully.eurder.infrastructure.exception;

public class NullPhoneNumberException extends RuntimeException {

    public NullPhoneNumberException() {
        super("Please provide a phone number!");
    }
}
