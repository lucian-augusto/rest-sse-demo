package com.lucianaugusto.auctionhouseserver.user.http.response;

import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import lombok.Getter;

@Getter
public class UserCreatedResponse {

    private String name;
    private String uri;

    public UserCreatedResponse(AuctionHouseUser user) {
        this.name = user.getName();
        this.uri = user.getUri();
    }
}
