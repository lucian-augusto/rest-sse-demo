package com.lucianaugusto.auctionhouseserver.auction.http.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NewAuctionRequest {

    private String auctionCode;
    private String productName;
    private String sellerName;
    private BigDecimal initialPrice;
    private int duration;
    private String description;
}
