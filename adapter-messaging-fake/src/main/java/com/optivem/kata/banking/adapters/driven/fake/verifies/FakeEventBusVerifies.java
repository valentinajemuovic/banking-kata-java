package com.optivem.kata.banking.adapters.driven.fake.verifies;

import com.optivem.kata.banking.adapters.driven.fake.FakeEventBus;

public class FakeEventBusVerifies {
    public static FakeEventBusVerify verifyThat(FakeEventBus eventBus) {
        return new FakeEventBusVerify(eventBus);
    }
}
