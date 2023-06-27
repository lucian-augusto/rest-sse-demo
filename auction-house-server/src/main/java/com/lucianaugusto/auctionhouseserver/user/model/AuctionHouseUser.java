package com.lucianaugusto.auctionhouseserver.user.model;

import com.lucianaugusto.auctionhouseserver.base.model.AbstractEntity;
import com.lucianaugusto.auctionhouseserver.user.http.request.NewUserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionHouseUser extends AbstractEntity {

    @Column(unique = true)
    private String name;
    private String uri;
    private String publicKey;

    public AuctionHouseUser(String name, String uri, String publicKey) {
        this.name = name;
        this.uri = uri;
        this.publicKey = publicKey;
    }

    public AuctionHouseUser(NewUserRequest request) {
        this.name = request.getName();
        this.uri = "Generic/uri";
        this.publicKey = "asdfasdfasdf";
    }
}
