package com.optivem.kata.banking.core.usecases.withdrawfunds;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.usecases.VoidResponse;

import static com.optivem.kata.banking.core.domain.common.extensions.Extension.extend;

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
        return extend(repository).findRequired(accountNumber);
    }
}
