package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountRequest;

public class ViewAccountRequestBuilder {

    public ViewAccountRequestBuilder() {
    }

    public static ViewAccountRequestBuilder aViewAccountRequest() {
        return new ViewAccountRequestBuilder();
    }

    public ViewAccountRequest build() {
        return new ViewAccountRequest();
    }
}
