package com.optivem.kata.banking.adapter.driven.generation.fake.internal;

public class NextElementIsNotSetupException extends RuntimeException {
    private static final String GENERATOR_DOES_NOT_HAVE_NEXT = "Generator does not have next element";

    public NextElementIsNotSetupException() {
        super(GENERATOR_DOES_NOT_HAVE_NEXT);
    }
}
