package com.optivem.kata.banking.adapters.driven.fake.verifies;

import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

public class BankAccountStorageVerifies {
    public static BankAccountStorageVerify verifyThat(BankAccountStorage storage) {
        return new BankAccountStorageVerify(storage);
    }
}
