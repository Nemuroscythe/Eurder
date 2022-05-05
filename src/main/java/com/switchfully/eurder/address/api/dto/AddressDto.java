package com.switchfully.eurder.address.api.dto;

import com.switchfully.eurder.postal_code.api.dto.PostalCodeDto;

public class AddressDto {
    private final String streetName;
    private final String streetNumber;
    private final PostalCodeDto postalCode;

    public AddressDto(String streetName, String streetNumber, PostalCodeDto postalCode) {
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

    public PostalCodeDto getPostalCode() {
        return postalCode;
    }

}
