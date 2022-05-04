package com.switchfully.eurder.customer.domain;


import com.switchfully.eurder.address.domain.Address;

import java.util.UUID;

public class Customer {

    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        this.customerId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
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
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
