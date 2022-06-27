package com.optivem.kata.banking.core.ports.driven;

import java.util.Optional;

public interface BankAccountDtoRepositoryPort {
    Optional<BankAccountDto> find(String accountNumber);

    void add(BankAccountDto bankAccount);

    void update(BankAccountDto bankAccount);
}
