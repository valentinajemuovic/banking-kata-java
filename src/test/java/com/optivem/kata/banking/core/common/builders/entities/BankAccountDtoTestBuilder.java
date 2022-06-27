package com.optivem.kata.banking.core.common.builders.entities;

import com.optivem.kata.banking.core.acl.BankAccountConverter;
import com.optivem.kata.banking.core.domain.accounts.*;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;

import java.time.LocalDate;

public class BankAccountDtoTestBuilder {
    public static BankAccountDtoTestBuilder bankAccount() {
        return new BankAccountDtoTestBuilder();
    }

    private long accountId;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;

    public BankAccountDtoTestBuilder() {
        this.accountId = BankAccountDefaults.DEFAULT_ACCOUNT_ID;
        this.accountNumber = BankAccountDefaults.DEFAULT_ACCOUNT_NUMBER;
        this.firstName = BankAccountDefaults.DEFAULT_FIRST_NAME;
        this.lastName = BankAccountDefaults.DEFAULT_LAST_NAME;
        this.openingDate = BankAccountDefaults.DEFAULT_OPENING_DATE;
        this.balance = BankAccountDefaults.DEFAULT_BALANCE;
    }

    public BankAccountDtoTestBuilder withAccountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    public BankAccountDtoTestBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankAccountDtoTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BankAccountDtoTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BankAccountDtoTestBuilder withOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public BankAccountDtoTestBuilder withBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public BankAccountDto build() {
        return BankAccountDto.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .firstName(firstName)
                .lastName(lastName)
                .openingDate(openingDate)
                .balance(balance)
                .build();
    }

    // TODO: VC: Consider separate file
    public BankAccount buildEntity() {
        return BankAccountConverter.toEntity(build());
    }

}
