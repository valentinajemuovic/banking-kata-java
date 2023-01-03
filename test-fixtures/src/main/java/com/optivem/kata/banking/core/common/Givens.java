package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.givens.*;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeDateTimeService;

public class Givens {
    public static BankAccountStorageGiven givenThat(BankAccountStorage storage) {
        return new BankAccountStorageGiven(storage);
    }

    public static FakeDateTimeServiceGiven givenThat(FakeDateTimeService service) {
        return new FakeDateTimeServiceGiven(service);
    }

    public static FacadeGiven givenThat(Facade facade) {
        return new FacadeGiven(facade);
    }
}
