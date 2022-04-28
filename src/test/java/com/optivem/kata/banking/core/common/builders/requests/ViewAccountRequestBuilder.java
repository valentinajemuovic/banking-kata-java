package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountRequest;

public class ViewAccountRequestBuilder {

    private static final String ACCOUNT_NUMBER = "GB51BARC20031816295685";

    public static ViewAccountRequestBuilder aViewAccountRequest() {
        return new ViewAccountRequestBuilder();
    }

    private String accountNumber;

    public ViewAccountRequestBuilder() {
        accountNumber(ACCOUNT_NUMBER);
    }

    public ViewAccountRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public ViewAccountRequest build() {
        return ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
