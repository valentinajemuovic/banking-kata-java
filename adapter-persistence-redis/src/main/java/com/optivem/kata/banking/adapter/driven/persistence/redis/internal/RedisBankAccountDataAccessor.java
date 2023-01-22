package com.optivem.kata.banking.adapter.driven.persistence.redis.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisBankAccountDataAccessor extends CrudRepository<BankAccountModel, String> {
}