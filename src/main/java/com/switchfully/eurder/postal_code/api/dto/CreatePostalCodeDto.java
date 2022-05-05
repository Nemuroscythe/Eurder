package com.switchfully.eurder.postal_code.api.dto;

import javax.validation.constraints.NotBlank;

public class CreatePostalCodeDto {
    @NotBlank(message = "postal code number is blank or null")
    private final String PostalCodeNumber;
    @NotBlank(message = "city is blank or null")
    private final String city;

    public CreatePostalCodeDto(String postalCodeNumber, String city) {
        this.PostalCodeNumber = postalCodeNumber;
        this.city = city;
    }

    public String getPostalCodeNumber() {
        return PostalCodeNumber;
    }

    public String getCity() {
        return city;
    }
}
