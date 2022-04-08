package com.switchfully.eurder.infrastructure.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalEmailException.class)
    protected void IllegalEmailException(IllegalEmailException ex,
                                           HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NullNameException.class)
    protected void NullNameException(NullNameException ex,
                                         HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NullAddressException.class)
    protected void NullAddressException(NullAddressException ex,
                                     HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NullPhoneNumberException.class)
    protected void NullPhoneNumberException(NullPhoneNumberException ex,
                                        HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler(NullDescriptionException.class)
    protected void NullDescriptionException(NullDescriptionException ex,
                                            HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler(NegativeNumberException.class)
    protected void NegativeNumberException(NegativeNumberException ex,
                                            HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }
// other @ExceptionHandler annotated methods handling different exceptions in different ways.
}
