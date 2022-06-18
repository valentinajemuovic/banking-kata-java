package com.optivem.kata.banking.core.usecases.viewaccount;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoreCalculator;

import static com.optivem.kata.banking.core.domain.extensions.Extension.extend;

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
        var bankAccount = findBankAccount(accountNumber);
        return getResponse(bankAccount);
    }

    private AccountNumber getAccountNumber(ViewAccountRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private BankAccount findBankAccount(AccountNumber accountNumber) {
        return extend(repository).findRequired(accountNumber);
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
