package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.infrastructure.exception.IllegalEmailException;
import com.switchfully.eurder.infrastructure.exception.NullAddressException;
import com.switchfully.eurder.infrastructure.exception.NullNameException;
import com.switchfully.eurder.infrastructure.utility.FieldValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final Logger serviceLogger = LoggerFactory.getLogger(CustomerService.class);

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
        FieldValidation.stringNullSafeBlankCheck(createCustomerDto.getFirstName(), new NullNameException(), "First name null or blank!", serviceLogger);
        FieldValidation.stringNullSafeBlankCheck(createCustomerDto.getLastName(), new NullNameException(), "Last name null or blank!", serviceLogger);
//        FieldValidation.stringNullSafeBlankCheck(createCustomerDto.getAddress(), new NullAddressException(), "Address null or blank!", serviceLogger);
        FieldValidation.stringNullSafeBlankCheck(createCustomerDto.getPhoneNumber(), new NullAddressException(), "Phone number null or blank!", serviceLogger);
    }

    private void emailValidation(String emailAddress) {
        if (!EmailValidator.getInstance()
                .isValid(emailAddress)) {
            throw new IllegalEmailException();
        }
    }

}
