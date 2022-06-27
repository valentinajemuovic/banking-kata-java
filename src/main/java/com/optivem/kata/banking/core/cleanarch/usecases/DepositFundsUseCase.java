package com.optivem.kata.banking.core.cleanarch.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.cleanarch.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.cleanarch.domain.common.extensions.Extension;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.depositfunds.DepositFundsRequest;

public class DepositFundsUseCase implements Command.Handler<DepositFundsRequest, VoidResponse> {

    private final BankAccountRepository repository;

    public DepositFundsUseCase(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public VoidResponse handle(DepositFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var bankAccount = findBankAccount(accountNumber);
        bankAccount.deposit(amount);
        repository.update(bankAccount);
        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumber(DepositFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(DepositFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }

    private BankAccount findBankAccount(AccountNumber accountNumber) {
        return Extension.extend(repository).findRequired(accountNumber);
    }
}
