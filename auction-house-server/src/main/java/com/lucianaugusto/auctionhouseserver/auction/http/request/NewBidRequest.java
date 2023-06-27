package com.lucianaugusto.auctionhouseserver.auction.http.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NewBidRequest {

    private String auctionCode;
    private String userName;
    private BigDecimal bidOffer;
}
