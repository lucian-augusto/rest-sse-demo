package com.lucianaugusto.auctionhouseserver.auction.error;

import com.lucianaugusto.auctionhouseserver.base.error.ErrorEnumInterface;

public enum AuctionErrorEnum implements ErrorEnumInterface {

    BIDDER_INVALID("This is not a valid bidder");

    private final String message;

    AuctionErrorEnum(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return this.message;
    }
}
