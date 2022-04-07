package com.switchfully.eurder.customer.infrastructure;

import com.switchfully.eurder.customer.infrastructure.exception.IllegalEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class Email {
    private final String emailAddress;

    public Email(String emailAddress) {
        emailValidation(emailAddress);
        this.emailAddress = emailAddress;
    }

    private void emailValidation(String emailAddress){
        if (!EmailValidator.getInstance()
                .isValid(emailAddress)) {
            throw new IllegalEmailException();
        }

    }
    public String getEmail() {
        return emailAddress;
    }
}
