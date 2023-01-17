package com.optivem.kata.banking.adapter.driven.messaging.fake;

import com.optivem.kata.banking.adapter.driven.base.EventBusTest;

public class FakeEventBusTest extends EventBusTest<FakeEventBus> {
    @Override
    protected FakeEventBus create() {
        return new FakeEventBus();
    }
}
