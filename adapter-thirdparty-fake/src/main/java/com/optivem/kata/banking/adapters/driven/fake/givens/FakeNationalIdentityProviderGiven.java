package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeNationalIdentityProvider;

public class FakeNationalIdentityProviderGiven {
    private final FakeNationalIdentityProvider provider;

    public FakeNationalIdentityProviderGiven(FakeNationalIdentityProvider provider) {
        this.provider = provider;
    }

    public void givenExists(String nationalIdentityNumber) {
        provider.givenExists(nationalIdentityNumber);
    }
}
