package com.optivem.kata.banking.adapter.driven.persistence.fake;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.RepositoryException;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.RepositoryMessages;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeBankAccountStorage implements BankAccountStorage {

    private final Map<String, BankAccountDto> bankAccounts;

    public FakeBankAccountStorage() {
        this.bankAccounts = new HashMap<>();
    }

    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        if (!contains(accountNumber)) {
            return Optional.empty();
        }

        var bankAccount = bankAccounts.get(accountNumber);
        var clonedBankAccount = clone(bankAccount);

        return Optional.of(clonedBankAccount);
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();
        var clonedBankAccount = clone(bankAccount);

        if (contains(accountNumber)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CONSTRAINT_VIOLATION);
        }

        bankAccounts.put(accountNumber, clonedBankAccount);
    }

    @Override
    public void update(BankAccountDto bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();

        if (!contains(accountNumber)) {
            throw new RepositoryException(RepositoryMessages.REPOSITORY_CANNOT_UPDATE_NON_EXISTENT);
        }

        var clonedBankAccount = clone(bankAccount);
        bankAccounts.put(accountNumber, clonedBankAccount);
    }

    public void givenExists(BankAccountDto bankAccount) {
        add(bankAccount);
    }

    public void shouldContain(BankAccountDto bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();
        var retrievedBankAccount = find(accountNumber);
        assertThat(retrievedBankAccount).isNotEmpty();
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
    }

    public void shouldNotContain(String accountNumber) {
        var retrievedBankAccount = find(accountNumber);
        assertThat(retrievedBankAccount).isEmpty();
    }

    private boolean contains(String accountNumber) {
        return bankAccounts.containsKey(accountNumber);
    }

    private BankAccountDto clone(BankAccountDto bankAccount) {
        var clone = new BankAccountDto();

        clone.setAccountId(bankAccount.getAccountId());
        clone.setAccountNumber(bankAccount.getAccountNumber());
        clone.setNationalIdentityNumber(bankAccount.getNationalIdentityNumber());
        clone.setFirstName(bankAccount.getFirstName());
        clone.setLastName(bankAccount.getLastName());
        clone.setBalance(bankAccount.getBalance());
        clone.setOpeningDate(bankAccount.getOpeningDate());

        return clone;
    }

}
