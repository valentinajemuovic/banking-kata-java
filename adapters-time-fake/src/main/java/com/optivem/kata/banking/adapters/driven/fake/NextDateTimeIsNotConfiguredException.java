package com.optivem.kata.banking.adapters.driven.fake;

public class NextDateTimeIsNotConfiguredException extends RuntimeException {
    private static final String GENERATOR_DOES_NOT_HAVE_NEXT = "Clock does not have next date-time";

    public NextDateTimeIsNotConfiguredException() {
        super(GENERATOR_DOES_NOT_HAVE_NEXT);
    }
}
