package com.optivem.kata.banking.core.common.factories;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.crud.usecases.OpenAccountUseCase;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;

public class CrudUseCaseFactory implements UseCaseFactory {
    @Override
    public Command.Handler<OpenAccountRequest, OpenAccountResponse> createOpenAccountHandler(NationalIdentityGateway nationalIdentityGateway, CustomerGateway customerGateway, BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        return new OpenAccountUseCase(nationalIdentityGateway, customerGateway, bankAccountStorage, accountIdGenerator, accountNumberGenerator, dateTimeService, eventBus);
    }

    @Override
    public Command.Handler<DepositFundsRequest, VoidResponse> createDepositFundsHandler(BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        return null;
    }
}
