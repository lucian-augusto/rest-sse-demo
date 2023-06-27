package com.lucianaugusto.auctionhouseserver.base.validation;

import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;
import com.lucianaugusto.auctionhouseserver.base.error.ErrorEnumInterface;

import java.util.Objects;

public class Validator {

    public static void assertEquals(Object expected, Object actual, ErrorEnumInterface error) {
        if (!expected.equals(actual)) {
            throwException(error);
        }
    }

    public static void assertNotEquals(Object expected, Object actual, ErrorEnumInterface error) {
        if (expected.equals(actual)) {
            throwException(error);
        }
    }

    public static void assertNotNull(Object actual, ErrorEnumInterface error) {
        if (Objects.isNull(actual)) {
            throwException(error);
        }
    }
    public static void isTrue(Boolean actual, ErrorEnumInterface error) {
        assertEquals(true, actual, error);
    }

    private static void throwException(ErrorEnumInterface error) {
        throw new BusinessRuleException(error.getMessage());
    }
}
