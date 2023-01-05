package com.optivem.kata.banking.adapters.restapi.spring;

public class FakeTokenProvider implements TokenProvider {
    @Override
    public String getToken() {
        return "Bearer SomeTokenABC";
    }
}
