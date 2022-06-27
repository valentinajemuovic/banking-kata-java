package com.optivem.kata.banking.core.usecases.openaccount;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.domain.accounts.*;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountUseCase implements Command.Handler<OpenAccountRequest, OpenAccountResponse> {
    private final BankAccountRepository bankAccountRepository;
    private final DateTimeService dateTimeService;


    public OpenAccountUseCase(BankAccountRepository bankAccountRepository, DateTimeService dateTimeService) {
        this.bankAccountRepository = bankAccountRepository;
        this.dateTimeService = dateTimeService;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        var accountHolderName = getAccountHolderName(request);
        var balance = getBalance(request);

        var bankAccount = createBankAccount(accountHolderName, balance);
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return AccountHolderName.of(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return Balance.of(request.getBalance());
    }

    private BankAccount createBankAccount(AccountHolderName accountHolderName, Balance balance) {
        var accountId = bankAccountRepository.nextAccountId();
        var accountNumber = bankAccountRepository.nextAccountNumber();
        var dateTime = dateTimeService.now();
        var openingDate = dateTime.toLocalDate();
        return new BankAccount(accountId, accountNumber, accountHolderName, openingDate, balance);
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
