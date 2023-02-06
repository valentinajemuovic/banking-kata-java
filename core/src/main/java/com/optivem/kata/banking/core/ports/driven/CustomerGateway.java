package com.optivem.kata.banking.core.ports.driven;

public interface CustomerGateway {
    boolean isBlacklisted(String nationalIdentityNumber);
}
