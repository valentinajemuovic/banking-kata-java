package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public record AccountHolderName(String firstName, String lastName) {

    public AccountHolderName(String firstName, String lastName) {
        this.firstName = guard(firstName).againstNullOrWhitespace(ValidationMessages.FIRST_NAME_EMPTY);
        this.lastName = guard(lastName).againstNullOrWhitespace(ValidationMessages.LAST_NAME_EMPTY);
    }
}
