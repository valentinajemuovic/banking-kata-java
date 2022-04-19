package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.usecases.UseCase;

public class OpenAccountUseCase implements UseCase<OpenAccountRequest, OpenAccountResponse> {

    private AccountNumberGenerator accountNumberGenerator;
    private BankAccountRepository bankAccountRepository;

    public OpenAccountUseCase(AccountNumberGenerator accountNumberGenerator, BankAccountRepository bankAccountRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.bankAccountRepository = bankAccountRepository;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        Guard.AgainstNullOrWhitespace(request.getFirstName(), ValidationMessages.FIRST_NAME_EMPTY);
        Guard.AgainstNullOrWhitespace(request.getLastName(), ValidationMessages.LAST_NAME_EMPTY);
        Guard.AgainstNegative(request.getInitialBalance(), ValidationMessages.INITIAL_BALANCE_NEGATIVE);

        var accountNumber = accountNumberGenerator.next();

        var bankAccount = new BankAccount(accountNumber);
        bankAccountRepository.add(bankAccount);

        return getResponse(accountNumber);
    }

    private OpenAccountResponse getResponse(String accountNumber) {
        var response = new OpenAccountResponse();
        response.setAccountNumber(accountNumber);
        return response;
    }
}
