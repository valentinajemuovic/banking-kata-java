package com.optivem.kata.banking.core.internal.cleanarch.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.Balance;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.AccountOpened;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.EventPublisher;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OpenAccountUseCase implements Command.Handler<OpenAccountRequest, OpenAccountResponse> {
    private final BankAccountRepository bankAccountRepository;
    private final DateTimeService dateTimeService;

    private final EventPublisher eventPublisher;


    public OpenAccountUseCase(BankAccountRepository bankAccountRepository, DateTimeService dateTimeService, EventPublisher eventPublisher) {
        this.bankAccountRepository = bankAccountRepository;
        this.dateTimeService = dateTimeService;
        this.eventPublisher = eventPublisher;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        var accountHolderName = getAccountHolderName(request);
        var balance = getBalance(request);

        var timestamp = dateTimeService.now();

        var bankAccount = createBankAccount(accountHolderName, balance, timestamp);
        bankAccountRepository.add(bankAccount);

        var accountOpened = getAccountOpened(bankAccount, timestamp);
        eventPublisher.publishEvent(accountOpened);

        return getResponse(bankAccount);
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return AccountHolderName.of(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return Balance.of(request.getBalance());
    }

    private BankAccount createBankAccount(AccountHolderName accountHolderName, Balance balance, LocalDateTime timestamp) {
        var accountId = bankAccountRepository.nextAccountId();
        var accountNumber = bankAccountRepository.nextAccountNumber();
        var openingDate = timestamp.toLocalDate();
        return new BankAccount(accountId, accountNumber, accountHolderName, openingDate, balance);
    }

    private AccountOpened getAccountOpened(BankAccount bankAccount, LocalDateTime timestamp) {
        return new AccountOpened(timestamp, bankAccount.getAccountId(), bankAccount.getAccountHolderName(), bankAccount.getBalance());
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
