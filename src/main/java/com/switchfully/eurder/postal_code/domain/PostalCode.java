package com.switchfully.eurder.postal_code.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "POSTAL_CODE")
public class PostalCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_seq")
    @SequenceGenerator(name = "postal_code_seq", sequenceName = "postal_code_seq", allocationSize = 1)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public String getPostalCodeNumber() {
        return postalCodeNumber;
    }

    public String getCity() {
        return city;
    }
}
