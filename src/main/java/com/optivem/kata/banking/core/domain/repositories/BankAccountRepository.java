package com.optivem.kata.banking.core.domain.repositories;

import com.optivem.kata.banking.core.domain.entities.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {

    Optional<BankAccount> find(String accountNumber);

    void add(BankAccount bankAccount);
}
