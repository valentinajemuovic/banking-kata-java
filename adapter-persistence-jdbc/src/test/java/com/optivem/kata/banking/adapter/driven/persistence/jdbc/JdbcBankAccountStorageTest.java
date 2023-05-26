package com.optivem.kata.banking.adapter.driven.persistence.jdbc;

import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.adapter.driven.base.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = BankingApplication.class)
@ActiveProfiles({ProfileNames.ADAPTER_PERSISTENCE_JDBC, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_MICROSERVICE_SIM, ProfileNames.ADAPTER_THIRDPARTY_SIM})
@Sql({"/bank_account_table.sql"})
public class JdbcBankAccountStorageTest extends BankAccountStorageTest {
    @Autowired
    public JdbcBankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        super(storage, accountIdGenerator, accountNumberGenerator);
    }
}
