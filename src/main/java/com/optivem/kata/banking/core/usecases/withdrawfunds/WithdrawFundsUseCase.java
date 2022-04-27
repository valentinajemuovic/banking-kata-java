package com.optivem.kata.banking.core.usecases.withdrawfunds;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.domain.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class WithdrawFundsUseCase implements UseCase<WithdrawFundsRequest, WithdrawFundsResponse> {

    private final BankAccountRepository repository;

    public WithdrawFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    public WithdrawFundsResponse handle(WithdrawFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var bankAccount = findBankAccount(accountNumber);
        bankAccount.withdraw(amount);
        repository.update(bankAccount);

        return getResponse(bankAccount);
    }

    private AccountNumber getAccountNumber(WithdrawFundsRequest request) {
        return new AccountNumber(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(WithdrawFundsRequest request) {
        return new TransactionAmount(request.getAmount());
    }

    private BankAccount findBankAccount(AccountNumber accountNumber) {
        var optionalBankAccount = repository.find(accountNumber);
        Guard.againstEmpty(optionalBankAccount, ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        return optionalBankAccount.get();
    }

    private WithdrawFundsResponse getResponse(BankAccount bankAccount) {
        var response = new WithdrawFundsResponse();
        response.setBalance(bankAccount.getBalance().getValue().value());
        return response;
    }
}
