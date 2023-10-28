package com.optivem.kata.banking.adapter.driven.persistence.redis;

import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.adapter.driven.base.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BankingApplication.class)
// @ContextConfiguration // TODO: VC: Probably ring this back and delete ActiveProfiles; ActiveProfiles was temporarily added
@ActiveProfiles({ ProfileNames.ADAPTER_PERSISTENCE_JPA, ProfileNames.ADAPTER_PERSISTENCE_REDIS, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_MICROSERVICE_SIM, ProfileNames.ADAPTER_THIRDPARTY_SIM})
class RedisBankAccountStorageTest extends BankAccountStorageTest {

    @Autowired
    public RedisBankAccountStorageTest(@Qualifier("RedisBankAccountStorage") BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        super(storage, accountIdGenerator, accountNumberGenerator);
    }
}