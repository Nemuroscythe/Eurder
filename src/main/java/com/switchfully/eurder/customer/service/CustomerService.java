package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.infrastructure.exception.IllegalEmailException;
import com.switchfully.eurder.infrastructure.exception.NullAddressException;
import com.switchfully.eurder.infrastructure.exception.NullNameException;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {
    private Logger serviceLogger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        customerFieldsValidation(createCustomerDto);

        Customer customer = customerMapper.toCustomer(createCustomerDto);
        serviceLogger.info("Saving customer...");
        customerRepository.saveCustomer(customer);
        return customerMapper.toDto(customer);
    }

    private void customerFieldsValidation(CreateCustomerDto createCustomerDto) {
        emailValidation(createCustomerDto.getEmailAddress());
        nullSafeBlankCheck(createCustomerDto.getFirstName(), new NullNameException(), "First name null or blank!");
        nullSafeBlankCheck(createCustomerDto.getLastName(), new NullNameException(), "Last name null or blank!");
        nullSafeBlankCheck(createCustomerDto.getAddress(), new NullAddressException(), "Address null or blank!");
        nullSafeBlankCheck(createCustomerDto.getPhoneNumber(), new NullAddressException(), "Phone number null or blank!");
    }

    private void emailValidation(String emailAddress) {
        if (!EmailValidator.getInstance()
                .isValid(emailAddress)) {
            throw new IllegalEmailException();
        }
    }

    private void nullSafeBlankCheck(String stringToCheck, RuntimeException exception, String errorMessage) {
        if (stringToCheck == null || stringToCheck.isBlank()) {
            serviceLogger.error(errorMessage);
            throw exception;
        }
    }
}
