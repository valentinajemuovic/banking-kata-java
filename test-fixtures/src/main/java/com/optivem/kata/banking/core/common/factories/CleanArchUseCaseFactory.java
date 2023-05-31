package com.optivem.kata.banking.core.common.factories;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.internal.cleanarch.acl.EventPublisherImpl;
import com.optivem.kata.banking.core.internal.cleanarch.usecases.DepositFundsUseCase;
import com.optivem.kata.banking.core.internal.cleanarch.usecases.OpenAccountUseCase;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;

public class CleanArchUseCaseFactory implements UseCaseFactory {

    @Override
    public Command.Handler<OpenAccountRequest, OpenAccountResponse> createOpenAccountHandler(NationalIdentityGateway nationalIdentityGateway, CustomerGateway customerGateway, BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        var repository = new BankAccountRepositoryImpl(bankAccountStorage, accountIdGenerator, accountNumberGenerator);
        var eventPublisher = new EventPublisherImpl(eventBus);
        return new OpenAccountUseCase(nationalIdentityGateway, customerGateway, repository, dateTimeService, eventPublisher);
    }

    @Override
    public Command.Handler<DepositFundsRequest, VoidResponse> createDepositFundsHandler(BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        var repository = new BankAccountRepositoryImpl(bankAccountStorage, accountIdGenerator, accountNumberGenerator);
        var eventPublisher = new EventPublisherImpl(eventBus);
        return new DepositFundsUseCase(repository, dateTimeService, eventPublisher);
    }
}
