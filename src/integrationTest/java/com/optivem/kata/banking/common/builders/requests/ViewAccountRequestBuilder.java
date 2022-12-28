package com.optivem.kata.banking.common.builders.requests;

import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountRequest;

public class ViewAccountRequestBuilder {

    private static final String DEFAULT_ACCOUNT_NUMBER = "GB51BARC20031816295685";

    public static ViewAccountRequestBuilder viewAccountRequest() {
        return new ViewAccountRequestBuilder();
    }

    private String accountNumber;

    public ViewAccountRequestBuilder() {
        this.accountNumber = DEFAULT_ACCOUNT_NUMBER;
    }

    public ViewAccountRequestBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public ViewAccountRequest build() {
        return ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
