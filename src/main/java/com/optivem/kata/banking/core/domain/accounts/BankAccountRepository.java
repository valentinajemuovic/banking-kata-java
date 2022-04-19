package com.optivem.kata.banking.core.domain.accounts;

import java.util.Optional;

public interface BankAccountRepository {

    Optional<BankAccount> find(String accountNumber);

    void add(BankAccount bankAccount);

    void update(BankAccount bankAccount);
}
