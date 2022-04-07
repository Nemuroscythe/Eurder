package com.switchfully.eurder.customers.service;

import com.switchfully.eurder.customers.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customers.api.dto.CustomerDto;
import com.switchfully.eurder.customers.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }

    public Customer toCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(
                createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getEmailAddress(),
                createCustomerDto.getAddress(),
                createCustomerDto.getPhoneNumber()
        );
    }
}
