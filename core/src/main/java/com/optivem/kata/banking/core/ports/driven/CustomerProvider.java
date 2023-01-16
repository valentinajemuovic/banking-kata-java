package com.optivem.kata.banking.core.ports.driven;

public interface CustomerProvider {
    boolean isBlacklisted(String nationalIdentityNumber);
}
