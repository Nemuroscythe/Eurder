package com.switchfully.eurder.customer.api.dto;

import com.switchfully.eurder.address.api.dto.CreateAddressDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateCustomerDto {

    @NotBlank(message = "firstName is null or blank")
    private final String firstName;
    @NotBlank(message = "lastName is null or blank")
    private final String lastName;
    @NotNull(message = "emailAddress is null")
    @Email(message = "emailAddress is in a wrong format")
    private final String emailAddress;
    @NotNull(message = "address is null")
    private final CreateAddressDto address;
    @NotBlank(message = "phoneNumber is null or blank")
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
