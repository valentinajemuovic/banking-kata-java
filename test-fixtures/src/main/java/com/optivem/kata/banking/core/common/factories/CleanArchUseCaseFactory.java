package com.optivem.kata.banking.core.common.factories;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.internal.cleanarch.acl.EventPublisherImpl;
import com.optivem.kata.banking.core.internal.cleanarch.usecases.OpenAccountUseCase;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;

public class CleanArchUseCaseFactory implements UseCaseFactory {

    @Override
    public Command.Handler<OpenAccountRequest, OpenAccountResponse> createOpenAccountHandler(NationalIdentityProvider nationalIdentityProvider, BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        var repository = new BankAccountRepositoryImpl(bankAccountStorage, accountIdGenerator, accountNumberGenerator);
        var eventPublisher = new EventPublisherImpl(eventBus);
        return new OpenAccountUseCase(repository, dateTimeService, eventPublisher);
    }
}
