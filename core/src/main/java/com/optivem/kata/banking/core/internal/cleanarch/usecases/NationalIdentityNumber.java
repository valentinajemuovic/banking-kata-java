package com.optivem.kata.banking.core.internal.cleanarch.usecases;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class NationalIdentityNumber {
  private final String value;

  private NationalIdentityNumber(String value) {
    checkValueNotNull(value);
    this.value = value;
  }

  public static NationalIdentityNumber of(String value) {
    return new NationalIdentityNumber(value);
  }

  private void checkValueNotNull(String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("National identity number cannot be null or empty");
    }
  }
}
