package com.lucianaugusto.auctionhouseserver.auction.providers;

import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import com.lucianaugusto.auctionhouseserver.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProvider {

    private final UserRepository repository;

    @Autowired
    public UserProvider(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<AuctionHouseUser> provideUserInfoByName(String name) {
        return repository.findByName(name);
    }
}
