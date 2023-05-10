package com.racan.atm.utils;

public class Constants {

    private Constants() {}

    public static final String PATTERN_ACCOUNT_NUMBER_AND_PIN = "^(\\d+)\\s(\\d+)\\s(\\d+)$";
    public static final String PATTERN_BALANCE_AND_OVERDRAFT = "^(\\d+)\\s(\\d+)$";
    public static final String PATTERN_WITHDRAW = "^W\\s(\\d+)";
    public static final String PATTERN_BALANCE = "^B$";
    public static final String PATTERN_PARAGRAPH = "\\n\\n";
    public static final String ERROR_AUTHENTICATION_FAILED = "ACCOUNT_ERR";
    public static final String ERROR_CUSTOMER_OUT_OF_FUNDS = "FUNDS_ERR";
    public static final String ERROR_ATM_OUT_OF_FUNDS = "ATM_ERR";


}
