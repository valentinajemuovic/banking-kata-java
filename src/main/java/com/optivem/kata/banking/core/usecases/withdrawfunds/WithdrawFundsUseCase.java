package com.optivem.kata.banking.core.usecases.withdrawfunds;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class WithdrawFundsUseCase implements UseCase<WithdrawFundsRequest, WithdrawFundsResponse> {

    private BankAccountRepository repository;

    public WithdrawFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    public WithdrawFundsResponse handle(WithdrawFundsRequest request) {
        Guard.againstNullOrWhitespace(request.getAccountNumber(), ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        Guard.againstNonPositive(request.getAmount(), ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);

        var optionalBankAccount = repository.find(request.getAccountNumber());

        if(optionalBankAccount.isEmpty()) {
            throw new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        }

        var bankAccount = optionalBankAccount.get();
        var balance = bankAccount.getBalance();
        balance -= request.getAmount();

        var response = new WithdrawFundsResponse();
        response.setBalance(balance);

        return response;
    }
}
