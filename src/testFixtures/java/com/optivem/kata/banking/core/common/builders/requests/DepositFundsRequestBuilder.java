package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;

public class DepositFundsRequestBuilder {

    private static final String DEFAULT_ACCOUNT_NUMBER = "GB51BARC20031816295685";
    private static final int DEFAULT_AMOUNT = 600;

    public static DepositFundsRequestBuilder depositFundsRequest() {
        return new DepositFundsRequestBuilder();
    }

    private String accountNumber;
    private int amount;

    public DepositFundsRequestBuilder() {
        this.accountNumber = DEFAULT_ACCOUNT_NUMBER;
        this.amount = DEFAULT_AMOUNT;
    }

    public DepositFundsRequestBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public DepositFundsRequestBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public DepositFundsRequest build() {
        return DepositFundsRequest.builder()
                .accountNumber(accountNumber)
                .amount(amount)
                .build();
    }
}
