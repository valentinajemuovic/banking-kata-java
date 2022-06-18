package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.accounts.Text;

public class ObjectGuard extends BaseGuard<Object> {
    public ObjectGuard(Object value) {
        super(value);
    }

}
