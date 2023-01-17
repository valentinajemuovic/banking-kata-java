package com.optivem.kata.banking.adapter.driven.fake;

import com.optivem.kata.banking.adapter.driven.EventBusTest;

public class FakeEventBusTest extends EventBusTest<FakeEventBus> {
    @Override
    protected FakeEventBus create() {
        return new FakeEventBus();
    }
}
