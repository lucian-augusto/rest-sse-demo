package com.lucianaugusto.auctionhouseserver.auction.service;

import com.lucianaugusto.auctionhouseserver.auction.exception.AuctionDoesNotExistException;
import com.lucianaugusto.auctionhouseserver.auction.http.request.NewAuctionRequest;
import com.lucianaugusto.auctionhouseserver.auction.http.request.NewBidRequest;
import com.lucianaugusto.auctionhouseserver.auction.model.Auction;
import com.lucianaugusto.auctionhouseserver.auction.providers.UserProvider;
import com.lucianaugusto.auctionhouseserver.auction.repository.AuctionRepository;
import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private final AuctionRepository repository;
    private final UserProvider userProvider;

    @Autowired
    public AuctionService(AuctionRepository repository, UserProvider userProvider) {
        this.repository = repository;
        this.userProvider = userProvider;
    }

    public Auction createNewAuction(NewAuctionRequest request) throws Exception {
        Optional<AuctionHouseUser> sellerOptional = userProvider.provideUserInfoByName(request.getSellerName());
        if (sellerOptional.isEmpty()) {
            throw new Exception("User not found");
        }

        try {
            Auction auction = new Auction(request.getAuctionCode(), request.getProductName(), sellerOptional.get(), request.getInitialPrice(), LocalDateTime.now(), request.getDuration(), request.getDescription());
            return repository.save(auction);
        } catch (Exception e) {
            throw  new Exception("Auction already exists");
        }
    }

    public List<Auction> listAllOpenAuctions() {
        return repository.findByEndDateTimeGreaterThan(LocalDateTime.now());
    }

    public Auction addNewBid(NewBidRequest newBidRequest) {
        Optional<Auction> auctionOptional = repository.findByCode(newBidRequest.getAuctionCode());

        if (auctionOptional.isEmpty()) {
            throw new AuctionDoesNotExistException();
        }

        Optional<AuctionHouseUser> bidderOptional = userProvider.provideUserInfoByName(newBidRequest.getUserName());

        Auction auction = auctionOptional.get();
        auction.addNewBid(bidderOptional.orElse(null), newBidRequest.getBidOffer());

        return repository.save(auction);
    }

}
