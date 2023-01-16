package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.Verifications;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;

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

    public void shouldThrowValidationException(String message) {
        Verifications.verifyThat(() -> facade.execute(request)).shouldThrowValidationException(message);
    }
}
