package com.lucianaugusto.auctionhouseserver.auction.exception;

import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;

public class AuctionClosedException extends BusinessRuleException {

    public AuctionClosedException() {
        super("The auction is closed");
    }
}
