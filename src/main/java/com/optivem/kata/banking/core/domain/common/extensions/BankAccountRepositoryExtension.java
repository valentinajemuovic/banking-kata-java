package com.optivem.kata.banking.core.domain.common.extensions;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.gateways.BankAccountRepository;
import com.optivem.kata.banking.core.domain.common.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.common.exceptions.ValidationMessages;

public class BankAccountRepositoryExtension {

    private final BankAccountRepository repository;

    public BankAccountRepositoryExtension(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount findRequired(AccountNumber accountNumber) {
        var optionalBankAccount = repository.find(accountNumber);

        if (optionalBankAccount.isEmpty()) {
            throw new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        }

        return optionalBankAccount.get();
    }
}
