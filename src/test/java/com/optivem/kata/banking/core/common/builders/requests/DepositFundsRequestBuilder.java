package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

public class DepositFundsRequestBuilder {

    private static final String ACCOUNT_NUMBER = "GB51BARC20031816295685";
    private static final int AMOUNT = 600;

    public static DepositFundsRequestBuilder aDepositFundsRequest() {
        return new DepositFundsRequestBuilder();
    }

    private String accountNumber;
    private int amount;

    public DepositFundsRequestBuilder() {
        accountNumber(ACCOUNT_NUMBER);
        amount(AMOUNT);
    }

    public DepositFundsRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public DepositFundsRequestBuilder amount(int amount) {
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
