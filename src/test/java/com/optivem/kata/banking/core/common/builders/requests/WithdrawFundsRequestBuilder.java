package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsRequest;

public class WithdrawFundsRequestBuilder {

    private static final String DEFAULT_ACCOUNT_NUMBER = "GB51BARC20031816295685";
    private static final int DEFAULT_AMOUNT = 300;

    public static WithdrawFundsRequestBuilder aWithdrawFundsRequest() {
        return new WithdrawFundsRequestBuilder();
    }

    private String accountNumber;
    private int amount;

    public WithdrawFundsRequestBuilder() {
        accountNumber(DEFAULT_ACCOUNT_NUMBER);
        amount(DEFAULT_AMOUNT);
    }

    public WithdrawFundsRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public WithdrawFundsRequestBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public WithdrawFundsRequest build() {
        return WithdrawFundsRequest.builder()
                .accountNumber(accountNumber)
                .amount(amount)
                .build();
    }
}
