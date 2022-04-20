package com.optivem.kata.banking.core.usecases.withdrawfunds;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class WithdrawFundsUseCase implements UseCase<WithdrawFundsRequest, WithdrawFundsResponse> {

    private final BankAccountRepository repository;

    public WithdrawFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    public WithdrawFundsResponse handle(WithdrawFundsRequest request) {
        Guard.againstNonPositive(request.getAmount(), ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);

        var bankAccount = getBankAccount(request);

        if(request.getAmount() > bankAccount.getBalance()) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }

        var balance = bankAccount.getBalance();
        balance -= request.getAmount();

        bankAccount.setBalance(balance);

        repository.update(bankAccount);

        return getResponse(bankAccount);
    }

    private BankAccount getBankAccount(WithdrawFundsRequest request) {
        Guard.againstNullOrWhitespace(request.getAccountNumber(), ValidationMessages.ACCOUNT_NUMBER_EMPTY);

        var accountNumber = new AccountNumber(request.getAccountNumber());
        var optionalBankAccount = repository.find(accountNumber);

        if(optionalBankAccount.isEmpty()) {
            throw new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        }

        return optionalBankAccount.get();
    }

    private WithdrawFundsResponse getResponse(BankAccount bankAccount) {
        var response = new WithdrawFundsResponse();
        response.setBalance(bankAccount.getBalance());
        return response;
    }
}
