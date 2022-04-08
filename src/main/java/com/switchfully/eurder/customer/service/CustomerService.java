package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.infrastructure.exception.IllegalEmailException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        emailValidation(createCustomerDto.getEmailAddress());

        Customer customer = customerMapper.toCustomer(createCustomerDto);
        customerRepository.saveCustomer(customer);
        return customerMapper.toDto(customer);
    }

    private void emailValidation(String emailAddress){
        if (!EmailValidator.getInstance()
                .isValid(emailAddress)) {
            throw new IllegalEmailException();
        }
    }
}
