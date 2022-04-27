package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.common.validators.StringValidator;

public class Validation {

    private Validation() {
    }

    public static StringValidator validate(String value) {
        return new StringValidator(value);
    }
}
