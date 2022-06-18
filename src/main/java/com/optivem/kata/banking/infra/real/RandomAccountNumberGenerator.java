package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.stereotype.Component;

@Component
public class RandomAccountNumberGenerator implements AccountNumberGenerator {
    private final ULID ulid;

    public RandomAccountNumberGenerator() {
        ulid = new ULID();
    }

    @Override
    public AccountNumber next() {
        var value = ulid.nextULID();
        return AccountNumber.of(value);
    }
}
