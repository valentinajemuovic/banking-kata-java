package com.optivem.kata.banking.core.domain.common.extensions;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Extension {

    public static BankAccountRepositoryExtension extend(BankAccountRepository repository) {
        return new BankAccountRepositoryExtension(repository);
    }
}
