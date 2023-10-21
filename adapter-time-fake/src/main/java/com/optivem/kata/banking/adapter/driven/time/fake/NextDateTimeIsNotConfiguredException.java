package com.optivem.kata.banking.adapter.driven.time.fake;

public class NextDateTimeIsNotConfiguredException extends RuntimeException {
    private static final String GENERATOR_DOES_NOT_HAVE_NEXT = "The Clock does not have next date-time";

    public NextDateTimeIsNotConfiguredException() {
        super(GENERATOR_DOES_NOT_HAVE_NEXT);
    }
}
