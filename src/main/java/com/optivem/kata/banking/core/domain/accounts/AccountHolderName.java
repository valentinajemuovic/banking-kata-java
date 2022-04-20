package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AccountHolderName {
    private final NonEmptyString firstName;
    private final NonEmptyString lastName;

    public AccountHolderName(String firstName, String lastName) {
        this.firstName = new NonEmptyString(firstName, ValidationMessages.FIRST_NAME_EMPTY);
        this.lastName = new NonEmptyString(lastName, ValidationMessages.LAST_NAME_EMPTY);
    }

    public NonEmptyString getFirstName() {
        return firstName;
    }

    public NonEmptyString getLastName() {
        return lastName;
    }
}
