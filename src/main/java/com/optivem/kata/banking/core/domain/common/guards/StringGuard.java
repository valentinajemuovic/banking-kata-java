package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.common.Validation;

public class StringGuard extends BaseGuard<String> {

    public StringGuard(String value) {
        super(value);
    }

    public void againstNullOrWhitespace(String message) {
        against(Validation::isNullOrWhitespace, message);
    }
}
