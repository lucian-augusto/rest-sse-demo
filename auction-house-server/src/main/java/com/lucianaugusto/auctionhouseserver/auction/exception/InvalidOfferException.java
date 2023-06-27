package com.lucianaugusto.auctionhouseserver.auction.exception;

import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;

public class InvalidOfferException extends BusinessRuleException {

    public InvalidOfferException() {
        super("New offers cannot be lower than the current one");
    }
}
