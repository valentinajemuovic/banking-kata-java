package com.optivem.kata.banking.infra.fake.accounts;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryException;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryMessages;

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
        var clonedBankAccount = new BankAccount(bankAccount);

        return Optional.of(clonedBankAccount);
    }

    @Override
    public void add(BankAccount bankAccount) {
        var key = bankAccount.getAccountNumber();
        var clonedBankAccount = new BankAccount(bankAccount);

        if(bankAccounts.containsKey(key)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CONSTRAINT_VIOLATION);
        }

        bankAccounts.put(key, clonedBankAccount);
    }

    @Override
    public void update(BankAccount bankAccount) {
        var key = bankAccount.getAccountNumber();

        if(!contains(key)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CANNOT_UPDATE_NON_EXISTENT);
        }

        var clonedBankAccount = new BankAccount(bankAccount);
        bankAccounts.put(key, clonedBankAccount);
    }

    private boolean contains(String accountNumber) {
        return bankAccounts.containsKey(accountNumber);
    }

}
