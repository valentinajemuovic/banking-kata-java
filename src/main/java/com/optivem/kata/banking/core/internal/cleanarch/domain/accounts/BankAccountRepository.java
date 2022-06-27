package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import java.util.Optional;

public interface BankAccountRepository {

    Optional<BankAccount> find(AccountNumber accountNumber);

    void add(BankAccount bankAccount);

    void update(BankAccount bankAccount);

    AccountId nextAccountId();

    AccountNumber nextAccountNumber();
}
