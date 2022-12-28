package com.optivem.kata.banking.common;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeDateTimeService;
import com.optivem.kata.banking.common.givens.*;
import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

public class Givens {
    public static BankAccountStorageGiven givenThat(BankAccountStorage storage) {
        return new BankAccountStorageGiven(storage);
    }

    public static FakeAccountNumberGeneratorGiven givenThat(FakeAccountNumberGenerator generator) {
        return new FakeAccountNumberGeneratorGiven(generator);
    }

    public static FakeAccountIdGeneratorGiven givenThat(FakeAccountIdGenerator generator) {
        return new FakeAccountIdGeneratorGiven(generator);
    }

    public static FakeDateTimeServiceGiven givenThat(FakeDateTimeService service) {
        return new FakeDateTimeServiceGiven(service);
    }

    public static FacadeGiven givenThat(Facade facade) {
        return new FacadeGiven(facade);
    }
}
