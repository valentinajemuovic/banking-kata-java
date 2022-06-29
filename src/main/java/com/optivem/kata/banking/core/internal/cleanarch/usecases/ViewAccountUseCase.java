package com.optivem.kata.banking.core.internal.cleanarch.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.ports.driver.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.ports.driver.viewaccount.ViewAccountResponse;
import org.springframework.stereotype.Component;

@Component
public class ViewAccountUseCase implements Command.Handler<ViewAccountRequest, ViewAccountResponse> {

    private final BankAccountRepository repository;
    private final ScoreCalculator scoreCalculator;

    public ViewAccountUseCase(BankAccountRepository repository, ScoreCalculator scoreCalculator) {
        this.repository = repository;
        this.scoreCalculator = scoreCalculator;
    }

    @Override
    public ViewAccountResponse handle(ViewAccountRequest request) {
        var accountNumber = getAccountNumber(request);
        var bankAccount = repository.findRequired(accountNumber);
        return getResponse(bankAccount);
    }

    private AccountNumber getAccountNumber(ViewAccountRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private ViewAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();
        var fullName = bankAccount.getAccountHolderName().toString();
        var balance = bankAccount.getBalance().toInt();
        var score = scoreCalculator.calculate(bankAccount);

        return ViewAccountResponse.builder()
                .accountNumber(accountNumber)
                .fullName(fullName)
                .balance(balance)
                .score(score)
                .build();
    }
}
