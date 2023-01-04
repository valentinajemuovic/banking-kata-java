package com.optivem.kata.banking.core.ports.driven;

public interface NationalIdentityProvider {
    boolean exists(String nationalIdentityNumber);
}
