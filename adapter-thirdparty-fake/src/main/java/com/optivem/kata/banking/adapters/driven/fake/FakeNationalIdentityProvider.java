package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FakeNationalIdentityProvider implements NationalIdentityProvider {
    private Set<String> nationalIdentityNumbers;

    public FakeNationalIdentityProvider() {
        this.nationalIdentityNumbers = new HashSet();
    }

    public void add(String nationalIdentityNumber) {
        this.nationalIdentityNumbers.add(nationalIdentityNumber);
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return nationalIdentityNumbers.contains(nationalIdentityNumber);
    }
}
