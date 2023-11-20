package com.optivem.kata.banking.adapter.driven.persistence.fake;

import com.optivem.kata.banking.adapter.driven.base.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.generation.random.RandomAccountIdGenerator;
import com.optivem.kata.banking.adapter.driven.generation.random.RandomAccountNumberGenerator;
import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FakeBankAccountStorageTest extends BankAccountStorageTest {
  private FakeBankAccountStorage bankAccountStorage;

  public FakeBankAccountStorageTest() {
    super(new FakeBankAccountStorage(), new RandomAccountIdGenerator(), new RandomAccountNumberGenerator());
  }

  @BeforeEach
  public void init() {
    this.bankAccountStorage = new FakeBankAccountStorage();
  }

  @Test
  void shouldAddAccountWhenAccountIsValid() {
    var accountNumber = "GB29NWBK60161331926819";
    var bankAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .build();

    bankAccountStorage.add(bankAccount);

    var retrievedAccount = bankAccountStorage.find(accountNumber);
    assertTrue(retrievedAccount.isPresent());
    assertEquals(bankAccount, retrievedAccount.get());
  }

  @Test
  void shouldThrowExceptionWhenAccountNumberExists() {
    var accountNumber = "GB33BUKB20201555555555";
    var bankAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .build();

    bankAccountStorage.add(bankAccount);

    var duplicateAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .build();

    assertThrows(RepositoryException.class, () -> bankAccountStorage.add(duplicateAccount));
  }

  @Test
  void shouldReturnEmptyWhenAccountNumberDoesNotExist() {
    var accountNumber = "GB94BARC10201530093459";
    var result = bankAccountStorage.find(accountNumber);
    assertEquals(Optional.empty(), result);
  }

  @Test
  void shouldUpdateAccountWhenAccountExists() {
    var accountNumber = "GB12BARC20201530093459";
    var initialAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .withBalance(100)
            .build();

    bankAccountStorage.add(initialAccount);

    var updatedAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .withBalance(200)
            .build();

    bankAccountStorage.update(updatedAccount);

    var retrievedAccount = bankAccountStorage.find(accountNumber);
    assertTrue(retrievedAccount.isPresent());
    assertEquals(200, retrievedAccount.get().getBalance());
  }

  @Test
  void shouldThrowExceptionWhenAccountDoesNotExist() {
    var accountNumber = "GB78BARC20201530093459";
    var nonExistentAccount = BankAccountDtoTestBuilder.bankAccount()
            .withAccountNumber(accountNumber)
            .build();

    assertThrows(RepositoryException.class, () -> bankAccountStorage.update(nonExistentAccount));
  }
}
