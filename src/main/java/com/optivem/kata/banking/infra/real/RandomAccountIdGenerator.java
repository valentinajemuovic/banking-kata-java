package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.domain.accounts.AccountIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class RandomAccountIdGenerator implements AccountIdGenerator {
    @Override
    public AccountId next() {
        // TODO: VC: Replace by Snowflake ID
        var value = (long)(Math.random() * 10000000);
        return AccountId.of(value);
    }
}
