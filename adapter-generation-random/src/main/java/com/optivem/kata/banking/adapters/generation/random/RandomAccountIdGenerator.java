package com.optivem.kata.banking.adapters.generation.random;

import com.optivem.kata.banking.adapter.driven.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.AdapterGenerationRandom)
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
