package com.optivem.kata.banking.core.usecases.viewaccount;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.ScoringService;
import com.optivem.kata.banking.core.usecases.UseCase;

import static com.optivem.kata.banking.core.domain.extensions.Extension.extend;

public class ViewAccountUseCase implements UseCase<ViewAccountRequest, ViewAccountResponse> {

    private final BankAccountRepository repository;
    private final ScoringService scoringService;

    public ViewAccountUseCase(BankAccountRepository repository, ScoringService scoringService) {
        this.repository = repository;
        this.scoringService = scoringService;
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
        var score = scoringService.calculateScore(bankAccount);

        return ViewAccountResponse.builder()
                .accountNumber(accountNumber)
                .fullName(fullName)
                .balance(balance)
                .score(score)
                .build();
    }
}
