package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.EventBusTest;

public class FakeEventBusTest extends EventBusTest<FakeEventBus> {
    @Override
    protected FakeEventBus create() {
        return new FakeEventBus();
    }
}
