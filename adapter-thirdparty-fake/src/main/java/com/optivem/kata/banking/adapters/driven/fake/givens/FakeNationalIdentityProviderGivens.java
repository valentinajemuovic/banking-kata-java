package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeNationalIdentityProvider;

public class FakeNationalIdentityProviderGivens {
    public static FakeNationalIdentityProviderGiven givenThat(FakeNationalIdentityProvider provider) {
        return new FakeNationalIdentityProviderGiven(provider);
    }
}
