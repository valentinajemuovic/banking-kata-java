package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

// TODO: VC: Perhaps doesn't belong with fakes because it can be used even for real, so moving to some common, e.g. adapters-persistence-tests where have common tests and common givens?
public class BankAccountStorageGivens {
    public static BankAccountStorageGiven givenThat(BankAccountStorage storage) {
        return new BankAccountStorageGiven(storage);
    }
}
