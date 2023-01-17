package com.optivem.kata.banking.adapters.persistence.mongo;

import com.optivem.kata.banking.adapter.driven.BankAccountStorageTest;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: You may refer to JpaBankAccountStorageTest for reference
@Disabled("TODO")
public class MongoBankAccountStorageTest extends BankAccountStorageTest {

    @Autowired
    public MongoBankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        super(storage, accountIdGenerator, accountNumberGenerator);
    }
}
