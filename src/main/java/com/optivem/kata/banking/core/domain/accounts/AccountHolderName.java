package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public record AccountHolderName(Text firstName, Text lastName) {

    public AccountHolderName {
        guard(firstName).againstNullOrWhitespace(ValidationMessages.FIRST_NAME_EMPTY);
        guard(lastName).againstNullOrWhitespace(ValidationMessages.LAST_NAME_EMPTY);
    }

    public static AccountHolderName of(String firstName, String lastName) {
        return new AccountHolderName(new Text(firstName), new Text(lastName));
    }
}
