package com.optivem.kata.banking.adapter.driven.generation.random;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.ADAPTER_GENERATION_RANDOM)
public class RandomAccountNumberGenerator implements AccountNumberGenerator {
    private final ULID ulid;

    public RandomAccountNumberGenerator() {
        ulid = new ULID();
    }

    @Override
    public String next() {
        return ulid.nextULID();
    }
}
