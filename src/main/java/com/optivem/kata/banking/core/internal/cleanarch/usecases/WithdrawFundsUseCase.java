package com.optivem.kata.banking.core.internal.cleanarch.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.extensions.Extension;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.withdrawfunds.WithdrawFundsRequest;

public class WithdrawFundsUseCase implements Command.Handler<WithdrawFundsRequest, VoidResponse> {

    private final BankAccountRepository repository;

    public WithdrawFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    public VoidResponse handle(WithdrawFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var bankAccount = findBankAccount(accountNumber);
        bankAccount.withdraw(amount);
        repository.update(bankAccount);
        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumber(WithdrawFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(WithdrawFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }

    private BankAccount findBankAccount(AccountNumber accountNumber) {
        return Extension.extend(repository).findRequired(accountNumber);
    }
}
