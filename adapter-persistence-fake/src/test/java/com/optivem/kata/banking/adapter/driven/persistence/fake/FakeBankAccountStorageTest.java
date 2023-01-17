package com.optivem.kata.banking.adapter.driven.persistence.fake;

import com.optivem.kata.banking.adapter.driven.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.generation.random.RandomAccountIdGenerator;
import com.optivem.kata.banking.adapter.driven.generation.random.RandomAccountNumberGenerator;
import com.optivem.kata.banking.adapter.driven.persistence.fake.FakeBankAccountStorage;

public class FakeBankAccountStorageTest extends BankAccountStorageTest {
    public FakeBankAccountStorageTest() {
        super(new FakeBankAccountStorage(), new RandomAccountIdGenerator(), new RandomAccountNumberGenerator());
    }
}
