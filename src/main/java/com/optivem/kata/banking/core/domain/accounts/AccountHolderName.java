package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

public class AccountHolderName {
    private final String firstName;
    private final String lastName;

    public AccountHolderName(String firstName, String lastName) {
        Guard.againstNullOrWhitespace(firstName, ValidationMessages.FIRST_NAME_EMPTY);
        Guard.againstNullOrWhitespace(lastName, ValidationMessages.LAST_NAME_EMPTY);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
