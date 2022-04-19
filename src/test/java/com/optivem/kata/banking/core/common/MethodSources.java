package com.optivem.kata.banking.core.common;

import java.util.stream.Stream;

public class MethodSources {

    private static final String BASE = "com.optivem.kata.banking.core.common.MethodSources#";
    public static final String NULL_EMPTY_WHITESPACE =  BASE + "nullEmptyWhitespace";
    public static final String NEGATIVE_INTEGERS =  BASE + "negativeIntegers";

    public static Stream<String> nullEmptyWhitespace() {
        return Stream.of(null, "", " ", "   ");
    }

    public static Stream<Integer> negativeIntegers() {
        return Stream.of(-1, -2, -10);
    }
}
