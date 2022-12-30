package com.optivem.kata.banking.adapters.driven.real;

import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class RandomAccountIdGenerator implements AccountIdGenerator {
    private static final int GENERATOR_ID = 0;

    private SnowflakeIdGenerator generator;

    public RandomAccountIdGenerator() {
        this.generator = SnowflakeIdGenerator.createDefault(GENERATOR_ID);
    }

    @Override
    public Long next() {
        return generator.next();
    }
}
