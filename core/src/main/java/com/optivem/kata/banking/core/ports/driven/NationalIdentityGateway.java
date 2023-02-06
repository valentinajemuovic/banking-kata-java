package com.optivem.kata.banking.core.ports.driven;

public interface NationalIdentityGateway {
    boolean exists(String nationalIdentityNumber);
}
