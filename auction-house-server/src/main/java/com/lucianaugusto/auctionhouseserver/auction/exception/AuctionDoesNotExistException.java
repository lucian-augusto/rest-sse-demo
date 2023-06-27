package com.lucianaugusto.auctionhouseserver.auction.exception;

import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;

public class AuctionDoesNotExistException extends BusinessRuleException {

    public AuctionDoesNotExistException() {
        super("Auction does not exist");
    }
}
