package com.switchfully.eurder.customer.api.dto;

import com.switchfully.eurder.address.api.dto.CreateAddressDto;

public class CreateCustomerDto {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final CreateAddressDto address;
    private final String phoneNumber;

    public CreateCustomerDto(String firstName, String lastName, String emailAddress, CreateAddressDto address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public CreateAddressDto getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
