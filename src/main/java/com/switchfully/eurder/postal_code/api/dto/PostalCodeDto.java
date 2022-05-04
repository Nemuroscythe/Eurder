package com.switchfully.eurder.postal_code.api.dto;

import java.util.Objects;

public class PostalCodeDto {
    private final String postalCodeNumber;
    private final String city;

    public PostalCodeDto(String postalCodeNumber, String city) {
        this.postalCodeNumber = postalCodeNumber;
        this.city = city;
    }

    public String getPostalCodeNumber() {
        return postalCodeNumber;
    }

    public String getCity() {
        return city;
    }
}
