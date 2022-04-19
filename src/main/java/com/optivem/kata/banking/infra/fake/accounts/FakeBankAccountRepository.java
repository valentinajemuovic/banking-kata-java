package com.optivem.kata.banking.infra.fake.accounts;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeBankAccountRepository implements BankAccountRepository {

    private final Map<String, BankAccount> bankAccounts;

    public FakeBankAccountRepository() {
        this.bankAccounts = new HashMap<>();
    }

    @Override
    public Optional<BankAccount> find(String accountNumber) {
        if(!bankAccounts.containsKey(accountNumber)) {
            return Optional.empty();
        }

        var bankAccount = bankAccounts.get(accountNumber);
        return Optional.of(bankAccount);
    }

    @Override
    public void add(BankAccount bankAccount) {
        var clonedBankAccount = new BankAccount(bankAccount);
        var key = bankAccount.getAccountNumber();
        bankAccounts.put(key, clonedBankAccount);
    }

    @Override
    public void update(BankAccount bankAccount) {
        throw new UnsupportedOperationException();
    }

}
