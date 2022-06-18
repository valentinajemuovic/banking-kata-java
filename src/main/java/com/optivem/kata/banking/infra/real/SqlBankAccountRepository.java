package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import java.util.Optional;

public class SqlBankAccountRepository implements BankAccountRepository {
    @Override
    public Optional<BankAccount> find(AccountNumber accountNumber) {
        return Optional.empty();
    }

    @Override
    public void add(BankAccount bankAccount) {

    }

    @Override
    public void update(BankAccount bankAccount) {

    }
}
