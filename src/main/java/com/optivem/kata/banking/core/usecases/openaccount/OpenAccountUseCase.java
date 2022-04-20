package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.accounts.AccountHolderName;
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
        var bankAccount = getBankAccount(request);
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private BankAccount getBankAccount(OpenAccountRequest request) {
        Guard.againstNegative(request.getInitialBalance(), ValidationMessages.INITIAL_BALANCE_NEGATIVE);

        var accountHolderName = new AccountHolderName(request.getFirstName(), request.getLastName());
        var accountNumber = accountNumberGenerator.next();

        return new BankAccount(accountNumber, accountHolderName, request.getInitialBalance());
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var response = new OpenAccountResponse();
        response.setAccountNumber(bankAccount.getAccountNumber().getValue());
        return response;
    }
}
