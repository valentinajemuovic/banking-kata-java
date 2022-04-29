package com.optivem.kata.banking.core.domain.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationMessages {

    public static final String FIRST_NAME_EMPTY = "First name is empty";
    public static final String LAST_NAME_EMPTY = "Last name is empty";
    public static final String BALANCE_NEGATIVE = "Initial balance is negative";
    public static final String ACCOUNT_NUMBER_EMPTY = "Account number is empty";
    public static final String ACCOUNT_NUMBER_NOT_EXIST = "Account number does not exist";
    public static final String NON_POSITIVE_TRANSACTION_AMOUNT = "Transaction amount is not positive";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds";
}
