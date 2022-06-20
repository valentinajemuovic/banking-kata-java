package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.domain.accounts.AccountIdGenerator;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class RandomAccountIdGenerator implements AccountIdGenerator {
    private static final int GENERATOR_ID = 0; // TODO: VC: Make configurable

    private SnowflakeIdGenerator generator;

    public RandomAccountIdGenerator() {
        this.generator = SnowflakeIdGenerator.createDefault(GENERATOR_ID);
    }

    @Override
    public AccountId next() {
        var value = generator.next();
        return AccountId.of(value);
    }
}
