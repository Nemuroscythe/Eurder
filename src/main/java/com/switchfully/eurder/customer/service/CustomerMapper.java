package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDtoInterface;
import com.switchfully.eurder.customer.domain.Customer;
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

    public Customer toCustomer(CustomerDtoInterface customerDto) {
        return new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmailAddress(),
                customerDto.getAddress(),
                customerDto.getPhoneNumber()
        );
    }
}
