package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.givens.*;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

public class Givens {


    public static FacadeGiven givenThat(Facade facade) {
        return new FacadeGiven(facade);
    }
}
