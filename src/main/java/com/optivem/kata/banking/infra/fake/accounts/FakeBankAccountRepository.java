package com.optivem.kata.banking.infra.fake.accounts;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryException;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeBankAccountRepository implements BankAccountRepository {

    private final Map<AccountNumber, BankAccount> bankAccounts;

    public FakeBankAccountRepository() {
        this.bankAccounts = new HashMap<>();
    }

    @Override
    public Optional<BankAccount> find(AccountNumber accountNumber) {
        if (!contains(accountNumber)) {
            return Optional.empty();
        }

        var bankAccount = bankAccounts.get(accountNumber);
        var clonedBankAccount = new BankAccount(bankAccount);

        return Optional.of(clonedBankAccount);
    }

    @Override
    public void add(BankAccount bankAccount) {
        var accountName = bankAccount.getAccountNumber();
        var clonedBankAccount = new BankAccount(bankAccount);

        if (contains(accountName)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CONSTRAINT_VIOLATION);
        }

        bankAccounts.put(accountName, clonedBankAccount);
    }

    @Override
    public void update(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();

        if (!contains(accountNumber)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CANNOT_UPDATE_NON_EXISTENT);
        }

        var clonedBankAccount = new BankAccount(bankAccount);
        bankAccounts.put(accountNumber, clonedBankAccount);
    }

    private boolean contains(AccountNumber accountNumber) {
        return bankAccounts.containsKey(accountNumber);
    }

}
