package com.lucianaugusto.auctionhouseserver.base.error;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(ErrorEnumInterface error) {
        super(error.getMessage());
    }
}
