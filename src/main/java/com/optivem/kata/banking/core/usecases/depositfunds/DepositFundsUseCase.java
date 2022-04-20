package com.optivem.kata.banking.core.usecases.depositfunds;

import com.optivem.kata.banking.core.usecases.UseCase;

public class DepositFundsUseCase implements UseCase<DepositFundsRequest, DepositFundsResponse> {
    @Override
    public DepositFundsResponse handle(DepositFundsRequest request) {
        return new DepositFundsResponse();
    }
}
