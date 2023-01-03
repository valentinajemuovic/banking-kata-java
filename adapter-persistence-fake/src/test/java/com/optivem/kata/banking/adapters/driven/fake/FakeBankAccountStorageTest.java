package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.BankAccountStorageTest;
import com.optivem.kata.banking.adapters.generation.random.RandomAccountIdGenerator;
import com.optivem.kata.banking.adapters.generation.random.RandomAccountNumberGenerator;

public class FakeBankAccountStorageTest extends BankAccountStorageTest {
    public FakeBankAccountStorageTest() {
        super(new FakeBankAccountStorage(), new RandomAccountIdGenerator(), new RandomAccountNumberGenerator());
    }
}
