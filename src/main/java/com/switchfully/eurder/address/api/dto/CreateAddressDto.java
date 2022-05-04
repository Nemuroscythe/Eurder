package com.switchfully.eurder.address.api.dto;

import com.switchfully.eurder.postal_code.api.dto.CreatePostalCodeDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAddressDto {

    @NotBlank(message = "streetName is blank or null")
    private final String streetName;
    @NotBlank(message = "streetNumber is blank or null")
    private final String streetNumber;
    @Valid
    @NotNull(message = "createPostalCode is null")
    private final CreatePostalCodeDto postalCode;

    public CreateAddressDto(String streetName, String streetNumber, CreatePostalCodeDto postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public CreatePostalCodeDto getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "CreateAddressDto{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postal code=" + postalCode +
                '}';
    }
}
