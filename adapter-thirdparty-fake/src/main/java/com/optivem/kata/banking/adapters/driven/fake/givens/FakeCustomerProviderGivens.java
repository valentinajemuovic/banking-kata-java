package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeCustomerProvider;
import com.optivem.kata.banking.adapters.driven.fake.FakeNationalIdentityProvider;

public class FakeCustomerProviderGivens {
    public static FakeCustomerProviderGiven givenThat(FakeCustomerProvider provider) {
        return new FakeCustomerProviderGiven(provider);
    }
}
