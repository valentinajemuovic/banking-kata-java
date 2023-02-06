package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.core.ports.driven.CustomerGateway;

import java.util.HashSet;
import java.util.Set;

public class FakeCustomerGateway implements CustomerGateway {

    private Set<String> blacklisted;

    public FakeCustomerGateway() {
        this.blacklisted = new HashSet<>();
    }

    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        return blacklisted.contains(nationalIdentityNumber);
    }

    public void setupBlacklisted(String nationalIdentityNumber) {
        blacklisted.add(nationalIdentityNumber);
    }
}
