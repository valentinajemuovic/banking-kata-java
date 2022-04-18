package com.optivem.kata.banking.core.domain.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
