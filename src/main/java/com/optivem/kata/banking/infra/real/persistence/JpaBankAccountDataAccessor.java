package com.optivem.kata.banking.infra.real.persistence;

import com.optivem.kata.banking.infra.real.persistence.BankAccountRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaBankAccountDataAccessor extends CrudRepository<BankAccountRecord, Long> {
    Optional<BankAccountRecord> findByAccountNumber(String accountNumber);
}
