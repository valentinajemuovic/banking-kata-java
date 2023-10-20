package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.Balance;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.ESBankAccount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ESBankAccountTest {
  private AccountId accountId;
  private AccountNumber accountNumber;
  private String nationalIdentityNumber;
  private AccountHolderName accountHolderName;
  private LocalDate openingDate;
  private Balance balance;

  @BeforeEach
  void init() {
    accountId = new AccountId(1L);
    accountNumber = AccountNumber.of("123456");
    nationalIdentityNumber = "NAT123";
    accountHolderName = AccountHolderName.of("John", "Doe");
    openingDate = LocalDate.now();
    balance = Balance.of(100);
  }

  @Test
  void shouldCreateAccountWhenArgumentAreValid() {
    var account = new ESBankAccount(accountId, accountNumber, nationalIdentityNumber,
            accountHolderName, openingDate, balance);

    assertEquals(accountId, account.getAccountId());
    assertEquals(accountNumber, account.getAccountNumber());
    assertEquals(nationalIdentityNumber, account.getNationalIdentityNumber());
    assertEquals(accountHolderName, account.getAccountHolderName());
    assertEquals(openingDate, account.getOpeningDate());
    assertEquals(balance, account.getBalance());
  }

  @Test
  void shouldThrowExceptionWithNullId() {
    assertThrows(ValidationException.class, () ->
            new ESBankAccount(null, accountNumber, nationalIdentityNumber,
                    accountHolderName, openingDate, balance));
  }

  @Test
  void shouldUpdateBalanceWhenDepositValidAmount() {
    var account = new ESBankAccount(accountId, accountNumber, nationalIdentityNumber,
            accountHolderName, openingDate, balance);

    var amount = TransactionAmount.of(50);
    account.deposit(amount);

    assertEquals(Balance.of(150), account.getBalance());
  }

  @Test
  void shouldUpdateBalanceWhenWithdrawValidAmount() {
    var account = new ESBankAccount(accountId, accountNumber, nationalIdentityNumber,
            accountHolderName, openingDate, balance);

    var amount = TransactionAmount.of(50);
    account.withdraw(amount);

    assertEquals(Balance.of(50), account.getBalance());
  }

  @Test
  void shouldThrowExceptionWithInvalidAmount() {
    var account = new ESBankAccount(accountId, accountNumber, nationalIdentityNumber,
            accountHolderName, openingDate, balance);

    var amount = TransactionAmount.of(200);
    assertThrows(ValidationException.class, () -> account.withdraw(amount));
  }
}
