package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;

import java.util.HashSet;
import java.util.Set;

public class FakeNationalIdentityProvider implements NationalIdentityProvider {
    private Set<String> nationalIdentityNumbers;

    public FakeNationalIdentityProvider() {
        this.nationalIdentityNumbers = new HashSet();
    }

    public void givenExists(String nationalIdentityNumber) {
        this.nationalIdentityNumbers.add(nationalIdentityNumber);
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return nationalIdentityNumbers.contains(nationalIdentityNumber);
    }
}
