package com.switchfully.eurder.postal_code.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "POSTAL_CODE")
public class PostalCode {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "POSTAL_CODE_NUMBER")
    private String postalCodeNumber;

    @Column(name = "CITY")
    private String city;

    public PostalCode() {
    }

    public PostalCode(String postalCodeNumber, String city) {
        this.postalCodeNumber = postalCodeNumber;
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public String getPostalCodeNumber() {
        return postalCodeNumber;
    }

    public String getCity() {
        return city;
    }
}
