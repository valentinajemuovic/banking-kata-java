package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;

import java.time.LocalDate;

/**
 * Alternative implementation of bank account - using event sourcing
 */
public class ESBankAccount {
    private final AccountId accountId;
    private final AccountNumber accountNumber;
    private final String nationalIdentityNumber;
    private final AccountHolderName accountHolderName;
    private final LocalDate openingDate;
    private Balance balance;

    public ESBankAccount(AccountId accountId, AccountNumber accountNumber, String nationalIdentityNumber,
                         AccountHolderName accountHolderName, LocalDate openingDate, Balance balance) {

        checkArguments(accountId, accountNumber, nationalIdentityNumber, accountHolderName, openingDate, balance);

        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.nationalIdentityNumber = nationalIdentityNumber;
        this.accountHolderName = accountHolderName;
        this.openingDate = openingDate;
        this.balance = balance;
    }

    public ESBankAccount(BankAccount other) {
        this(other.getAccountId(), other.getAccountNumber(), other.getNationalIdentityNumber(),
                other.getAccountHolderName(), other.getOpeningDate(), other.getBalance());
    }

    public AccountId getAccountId() { return accountId; }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public String getNationalIdentityNumber() {
        return nationalIdentityNumber;
    }

    public AccountHolderName getAccountHolderName() {
        return accountHolderName;
    }

    public LocalDate getOpeningDate() { return openingDate; }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(TransactionAmount amount) {
        balance = balance.add(amount);
    }

    public void withdraw(TransactionAmount amount) {
        if (amount.greaterThan(balance)) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }
        balance = balance.subtract(amount);
    }

    private void checkArguments(Object... args) {
        String[] validationMessages = {
                ValidationMessages.ACCOUNT_ID_EMPTY,
                ValidationMessages.ACCOUNT_NUMBER_EMPTY,
                ValidationMessages.NATIONAL_IDENTITY_NUMBER_EMPTY,
                ValidationMessages.ACCOUNT_HOLDER_NAME_EMPTY,
                ValidationMessages.OPENING_DATE_EMPTY,
                ValidationMessages.BALANCE_EMPTY
        };

        for (int i = 0; i < args.length; i++) {
            Guard.guard(args[i]).againstNull(validationMessages[i]);
        }
    }
}
