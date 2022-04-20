package com.optivem.kata.banking.core.usecases.withdrawfunds;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class WithdrawFundsUseCase implements UseCase<WithdrawFundsRequest, WithdrawFundsResponse> {

    private final BankAccountRepository repository;

    public WithdrawFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    public WithdrawFundsResponse handle(WithdrawFundsRequest request) {
        var accountNumber = new AccountNumber(request.getAccountNumber());
        var amount = new TransactionAmount(request.getAmount());
        var bankAccount = getBankAccount(accountNumber);

        bankAccount.withdraw(amount);

        repository.update(bankAccount);

        return getResponse(bankAccount);
    }

    private BankAccount getBankAccount(AccountNumber accountNumber) {
        var optionalBankAccount = repository.find(accountNumber);

        if(optionalBankAccount.isEmpty()) {
            throw new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        }

        return optionalBankAccount.get();
    }

    private WithdrawFundsResponse getResponse(BankAccount bankAccount) {
        var response = new WithdrawFundsResponse();
        response.setBalance(bankAccount.getBalance().getValue());
        return response;
    }
}
