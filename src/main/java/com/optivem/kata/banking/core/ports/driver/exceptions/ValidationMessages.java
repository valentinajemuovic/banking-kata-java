package com.optivem.kata.banking.core.ports.driver.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationMessages {

    public static final String FIRST_NAME_EMPTY = "First name is empty";
    public static final String LAST_NAME_EMPTY = "Last name is empty";
    public static final String BALANCE_NEGATIVE = "Balance is negative";
    public static final String ACCOUNT_NUMBER_EMPTY = "Account number is empty";
    public static final String ACCOUNT_NUMBER_NOT_EXIST = "Account number does not exist";
    public static final String AMOUNT_NOT_POSITIVE = "Amount is not positive";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds";
    public static final String ACCOUNT_HOLDER_NAME_EMPTY = "Account holder name is empty";
    public static final String OPENING_DATE_EMPTY = "Opening date is empty";
    public static final String BALANCE_EMPTY = "Balance is empty";
    public static final String ACCOUNT_ID_EMPTY = "Account ID is empty";
    public static final String ACCOUNT_ID_NON_POSITIVE = "Account ID is not positive";
}
