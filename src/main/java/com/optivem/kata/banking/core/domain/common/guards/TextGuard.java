package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.accounts.Text;

public class TextGuard extends BaseGuard<Text> {

    public TextGuard(Text value) {
        super(value);
    }

    public Text againstNullOrWhitespace(String message) {
        return against(value::isNullOrWhitespace, message);
    }
}
