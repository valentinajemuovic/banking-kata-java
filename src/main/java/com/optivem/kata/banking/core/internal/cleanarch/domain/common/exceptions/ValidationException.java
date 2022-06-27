package com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
