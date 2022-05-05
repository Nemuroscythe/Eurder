package com.switchfully.eurder.customer.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> customersById;

    public CustomerRepository() {
        customersById = new HashMap<>();
    }

    public void saveCustomer(Customer customer) {
        customersById.put(customer.getCustomerId(), customer);
    }

    public boolean exist(String customerId) {
        return customersById.containsKey(customerId);
    }
}
