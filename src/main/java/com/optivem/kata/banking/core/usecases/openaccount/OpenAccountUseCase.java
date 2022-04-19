package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.usecases.UseCase;

public class OpenAccountUseCase implements UseCase<OpenAccountRequest, OpenAccountResponse> {

    private final AccountNumberGenerator accountNumberGenerator;
    private final BankAccountRepository bankAccountRepository;

    public OpenAccountUseCase(AccountNumberGenerator accountNumberGenerator, BankAccountRepository bankAccountRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.bankAccountRepository = bankAccountRepository;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        Guard.againstNullOrWhitespace(request.getFirstName(), ValidationMessages.FIRST_NAME_EMPTY);
        Guard.againstNullOrWhitespace(request.getLastName(), ValidationMessages.LAST_NAME_EMPTY);
        Guard.againstNegative(request.getInitialBalance(), ValidationMessages.INITIAL_BALANCE_NEGATIVE);

        var accountNumber = accountNumberGenerator.next();

        var bankAccount = new BankAccount(accountNumber, request.getFirstName(), request.getLastName(), request.getInitialBalance());
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var response = new OpenAccountResponse();
        response.setAccountNumber(bankAccount.getAccountNumber().value());
        return response;
    }
}
