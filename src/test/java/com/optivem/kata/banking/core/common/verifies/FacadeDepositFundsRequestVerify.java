package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class FacadeDepositFundsRequestVerify {
    private final Facade facade;
    private final DepositFundsRequest request;

    public FacadeDepositFundsRequestVerify(Facade facade, DepositFundsRequest request) {
        this.facade = facade;
        this.request = request;
    }

    public void shouldExecuteSuccessfully() {
        facade.execute(request);
    }
}