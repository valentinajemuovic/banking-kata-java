package com.optivem.kata.banking;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    private final Map<String, CustomerDto> customers;

    public CustomerService() {
        customers = new HashMap<>();
    }

    public Optional<CustomerDto> getCustomer(String id) {
        if(!customers.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(customers.get(id));
    }
}
