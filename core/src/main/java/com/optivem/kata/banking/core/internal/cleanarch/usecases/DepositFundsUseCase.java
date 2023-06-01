package com.optivem.kata.banking.core.internal.cleanarch.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.FundsDeposited;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.EventPublisher;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import com.optivem.kata.banking.core.ports.driven.events.FundsDepositedDto;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;
import jdk.jfr.Event;

public class DepositFundsUseCase implements Command.Handler<DepositFundsRequest, VoidResponse> {

    private final BankAccountRepository repository;

    private final DateTimeService dateTimeService;

    private final EventPublisher eventPublisher;

    public DepositFundsUseCase(BankAccountRepository repository, DateTimeService dateTimeService, EventPublisher eventPublisher) {
        this.repository = repository;
        this.dateTimeService = dateTimeService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public VoidResponse handle(DepositFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var bankAccount = repository.findRequired(accountNumber);
        bankAccount.deposit(amount);
        repository.update(bankAccount);

        var timestamp = dateTimeService.now();
        var event = new FundsDeposited(timestamp, accountNumber, amount);
        eventPublisher.publishEvent(event);

        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumber(DepositFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(DepositFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }
}
