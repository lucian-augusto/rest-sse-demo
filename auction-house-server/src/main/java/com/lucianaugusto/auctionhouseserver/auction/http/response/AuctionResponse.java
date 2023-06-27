package com.lucianaugusto.auctionhouseserver.auction.http.response;

import com.lucianaugusto.auctionhouseserver.auction.model.Auction;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class AuctionResponse {

    private String code;
    private String name;
    private String seller;
    private BigDecimal price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;
    public AuctionResponse(Auction auction) {
        this.code = auction.getCode();
        this.name = auction.getName();
        this.seller = auction.getSeller().getName();
        this.price = auction.getPrice();
        this.startDateTime = auction.getStartDateTime();
        this.endDateTime = auction.getEndDateTime();
        this.description = auction.getDescription();
    }
}
