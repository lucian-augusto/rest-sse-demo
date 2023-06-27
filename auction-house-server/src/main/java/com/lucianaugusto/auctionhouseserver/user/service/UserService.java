package com.lucianaugusto.auctionhouseserver.user.service;

import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;
import com.lucianaugusto.auctionhouseserver.user.error.UserErrorEnum;
import com.lucianaugusto.auctionhouseserver.user.http.request.NewUserRequest;
import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import com.lucianaugusto.auctionhouseserver.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public AuctionHouseUser createNewUser(NewUserRequest request) {
        try {
            AuctionHouseUser user = new AuctionHouseUser(request);
            return repository.save(user);
        } catch (Exception e) {
            throw new BusinessRuleException(UserErrorEnum.USER_ALREADY_EXISTS);
        }
    }
}
