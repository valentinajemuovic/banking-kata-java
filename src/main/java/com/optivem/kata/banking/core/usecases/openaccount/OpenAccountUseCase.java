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
        var accountHolderName = getAccountHolderName(request);
        var balance = getBalance(request);

        var bankAccount = createBankAccount(accountHolderName, balance);
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return new AccountHolderName(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return new Balance(new Money(request.getInitialBalance()));
    }

    private BankAccount createBankAccount(AccountHolderName accountHolderName, Balance balance) {
        var accountNumber = accountNumberGenerator.next();
        return new BankAccount(accountNumber, accountHolderName, balance);
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var response = new OpenAccountResponse();
        response.setAccountNumber(bankAccount.getAccountNumber().value());
        return response;
    }
}
