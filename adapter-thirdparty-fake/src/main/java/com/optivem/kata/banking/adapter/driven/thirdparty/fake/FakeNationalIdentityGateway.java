package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityGateway;

import java.util.HashSet;
import java.util.Set;

public class FakeNationalIdentityGateway implements NationalIdentityGateway {
    private Set<String> nationalIdentityNumbers;

    public FakeNationalIdentityGateway() {
        this.nationalIdentityNumbers = new HashSet();
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return nationalIdentityNumbers.contains(nationalIdentityNumber);
    }

    public void setupExistent(String nationalIdentityNumber) {
        this.nationalIdentityNumbers.add(nationalIdentityNumber);
    }
}
