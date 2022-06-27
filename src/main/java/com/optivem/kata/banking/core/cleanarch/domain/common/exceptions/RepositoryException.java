package com.optivem.kata.banking.core.cleanarch.domain.common.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }
}
