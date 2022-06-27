package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {

    Optional<BankAccount> find(AccountNumber accountNumber);

    void add(BankAccount bankAccount);

    void update(BankAccount bankAccount);

    AccountId nextAccountId();

    AccountNumber nextAccountNumber();
}
