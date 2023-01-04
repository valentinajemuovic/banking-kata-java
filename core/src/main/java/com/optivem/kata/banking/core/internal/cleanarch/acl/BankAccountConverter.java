package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.*;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;

public class BankAccountConverter {
    public static BankAccount toEntity(BankAccountDto dto) {
        var accountId = AccountId.of(dto.getAccountId());
        var accountNumber = AccountNumber.of(dto.getAccountNumber());
        var nationalIdentityNumber = dto.getNationalIdentityNumber();
        var accountHolderName = AccountHolderName.of(dto.getFirstName(), dto.getLastName());
        var openingDate = dto.getOpeningDate();
        var balance = Balance.of(dto.getBalance());

        return new BankAccount(accountId, accountNumber, nationalIdentityNumber, accountHolderName, openingDate, balance);
    }

    public static BankAccountDto toDto(BankAccount bankAccount) {
        var accountId = bankAccount.getAccountId().toLong();
        var accountNumber = bankAccount.getAccountNumber().toString();
        var nationalIdentityNumber = bankAccount.getNationalIdentityNumber();
        var firstName = bankAccount.getAccountHolderName().firstName().toString();
        var lastName = bankAccount.getAccountHolderName().lastName().toString();
        var openingDate = bankAccount.getOpeningDate();
        var balance = bankAccount.getBalance().toInt();

        var dto = new BankAccountDto();
        dto.setAccountId(accountId);
        dto.setAccountNumber(accountNumber);
        dto.setNationalIdentityNumber(nationalIdentityNumber);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setOpeningDate(openingDate);
        dto.setBalance(balance);

        return dto;
    }
}
