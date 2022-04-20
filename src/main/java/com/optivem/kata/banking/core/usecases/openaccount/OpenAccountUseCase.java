package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.domain.accounts.*;
import com.optivem.kata.banking.core.usecases.UseCase;

public class OpenAccountUseCase implements UseCase<OpenAccountRequest, OpenAccountResponse> {

    private final AccountNumberGenerator accountNumberGenerator;
    private final BankAccountRepository bankAccountRepository;

    public OpenAccountUseCase(AccountNumberGenerator accountNumberGenerator, BankAccountRepository bankAccountRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.bankAccountRepository = bankAccountRepository;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        var accountHolderName = new AccountHolderName(request.getFirstName(), request.getLastName());
        var balance = new Balance(request.getInitialBalance());

        var bankAccount = getBankAccount(accountHolderName, balance);
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private BankAccount getBankAccount(AccountHolderName accountHolderName, Balance balance) {
        var accountNumber = accountNumberGenerator.next();
        return new BankAccount(accountNumber, accountHolderName, balance);
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var response = new OpenAccountResponse();
        response.setAccountNumber(bankAccount.getAccountNumber().getValue());
        return response;
    }
}
