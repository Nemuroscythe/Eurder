package com.switchfully.eurder.address.api.dto;

import com.switchfully.eurder.postal_code.api.dto.PostalCodeDto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(streetName, that.streetName) && Objects.equals(streetNumber, that.streetNumber) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode);
    }
}
