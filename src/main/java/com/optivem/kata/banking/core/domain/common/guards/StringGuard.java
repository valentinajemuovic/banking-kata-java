package com.optivem.kata.banking.core.domain.common.guards;

import static com.optivem.kata.banking.core.domain.common.Validation.validate;

public class StringGuard extends BaseGuard<String> {

    public StringGuard(String value) {
        super(value);
    }

    public String againstNullOrWhitespace(String message) {
        return against(validate(value)::isNullOrWhitespace, message);
    }
}
