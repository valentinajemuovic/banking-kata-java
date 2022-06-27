package com.optivem.kata.banking.core.cleanarch.domain.common.extensions;

import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccountRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Extension {

    public static BankAccountRepositoryExtension extend(BankAccountRepository repository) {
        return new BankAccountRepositoryExtension(repository);
    }
}
