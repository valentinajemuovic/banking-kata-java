package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

import static com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard.guard;

@EqualsAndHashCode
public class BankAccount {
    private final AccountId accountId;
    private final AccountNumber accountNumber;
    private final AccountHolderName accountHolderName;
    private final LocalDate openingDate;
    private Balance balance;

    public BankAccount(AccountId accountId, AccountNumber accountNumber, AccountHolderName accountHolderName, LocalDate openingDate, Balance balance) {
        Guard.guard(accountId).againstNull(ValidationMessages.ACCOUNT_ID_EMPTY);
        guard(accountNumber).againstNull(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        Guard.guard(accountHolderName).againstNull(ValidationMessages.ACCOUNT_HOLDER_NAME_EMPTY);
        Guard.guard(openingDate).againstNull(ValidationMessages.OPENING_DATE_EMPTY);
        Guard.guard(balance).againstNull(ValidationMessages.BALANCE_EMPTY);

        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.openingDate = openingDate;
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this(other.getAccountId(), other.getAccountNumber(), other.getAccountHolderName(), other.getOpeningDate(), other.getBalance());
    }

    public AccountId getAccountId() { return accountId; }

    public AccountNumber getAccountNumber() {
        return accountNumber;
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
}
