package com.optivem.kata.banking.core.domain.extensions;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

public class Extension {

    private Extension() {
    }

    public static BankAccountRepositoryExtension extend(BankAccountRepository repository) {
        return new BankAccountRepositoryExtension(repository);
    }
}
