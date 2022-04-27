package com.optivem.kata.banking.core.domain.common.guards;

import java.util.Optional;

public class OptionalGuard<V> extends BaseGuard<Optional<V>> {
    public OptionalGuard(Optional<V> value) {
        super(value);
    }

    public void againstEmpty(String message) {
        against(value::isEmpty, message);
    }
}
