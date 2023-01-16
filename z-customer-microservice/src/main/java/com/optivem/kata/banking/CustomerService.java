package com.optivem.kata.banking;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class CustomerService {

    private HashMap<String, CustomerDto> customers;

    public CustomerService() {
        this.customers = new HashMap<>();
        customers.put("SIM_3", new CustomerDto());
    }

    public CustomerDto getCustomer(String id) {
        return customers.getOrDefault(id, null);
    }
}
