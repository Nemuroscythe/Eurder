package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.address.domain.Address;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String emailAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public UUID getCustomerId() {
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

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
