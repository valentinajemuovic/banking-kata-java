package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard.guard;

public record AccountId(long value) {
    public static AccountId of(long value) {
        guard(value).againstNonPositive(ValidationMessages.ACCOUNT_ID_NON_POSITIVE);
        return new AccountId(value);
    }

    public long toLong() {
        return value;
    }
}
