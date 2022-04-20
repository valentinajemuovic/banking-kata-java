package com.optivem.kata.banking.core.usecases.depositfunds;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class DepositFundsUseCase implements UseCase<DepositFundsRequest, DepositFundsResponse> {

    private final BankAccountRepository repository;

    public DepositFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepositFundsResponse handle(DepositFundsRequest request) {
        var accountNumber = new AccountNumber(request.getAccountNumber());
        var amount = new TransactionAmount(request.getAmount());

        var bankAccount = getBankAccount(accountNumber);
        bankAccount.deposit(amount);
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

    private DepositFundsResponse getResponse(BankAccount bankAccount) {
        var response = new DepositFundsResponse();
        response.setBalance(bankAccount.getBalance().getValue());
        return response;
    }
}
