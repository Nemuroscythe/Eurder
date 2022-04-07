package com.switchfully.eurder.infrastructure.exception;

public class IllegalEmailException extends RuntimeException {

    public IllegalEmailException() {
        super("The email address provided is not correct!");
    }
}
