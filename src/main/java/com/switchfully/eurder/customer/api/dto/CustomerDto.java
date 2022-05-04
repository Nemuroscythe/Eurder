package com.switchfully.eurder.customer.api.dto;

import com.switchfully.eurder.address.api.dto.AddressDto;
import com.switchfully.eurder.address.api.dto.CreateAddressDto;

public class CustomerDto {

    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final AddressDto address;
    private final String phoneNumber;

    public CustomerDto(String customerId, String firstName, String lastName, String emailAddress, AddressDto address, String phoneNumber) {
        this.customerId = customerId;
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

    public AddressDto getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
