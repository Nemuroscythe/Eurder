package com.switchfully.eurder.customers.service;

import com.switchfully.eurder.customers.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customers.api.dto.CustomerDto;
import com.switchfully.eurder.customers.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto){
        Customer customer = customerMapper.toCustomer(createCustomerDto);
        return customerMapper.toDto(customer);
    }
}
