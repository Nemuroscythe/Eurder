package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.infrastructure.Email;

import java.util.UUID;

public class Customer {

    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final Email emailAddress;
    private final String address;
    private final String phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, String address, String phoneNumber) {
        this.customerId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = new Email(emailAddress);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress.getEmail();
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
