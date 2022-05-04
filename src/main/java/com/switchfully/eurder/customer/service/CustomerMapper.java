package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.address.service.AddressMapper;
import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final AddressMapper addressMapper;

    public CustomerMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                addressMapper.toDto(customer.getAddress()),
                customer.getPhoneNumber());
    }

    public Customer toCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(
                createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getEmailAddress(),
                addressMapper.toAddress(createCustomerDto.getAddress()),
                createCustomerDto.getPhoneNumber()
        );
    }
}
