package com.optivem.kata.banking.core.common.builders.ports.driven;

import com.optivem.kata.banking.core.internal.cleanarch.acl.BankAccountConverter;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;

public final class BankAccountTestConverter {
  private BankAccountTestConverter() {
  }

  public static BankAccount toEntity(BankAccountDto bankAccountDto) {
    return BankAccountConverter.toEntity(bankAccountDto);
  }
}
