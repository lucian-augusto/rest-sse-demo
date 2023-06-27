package com.lucianaugusto.auctionhouseserver.auction.model;

import com.lucianaugusto.auctionhouseserver.auction.error.AuctionErrorEnum;
import com.lucianaugusto.auctionhouseserver.auction.exception.AuctionClosedException;
import com.lucianaugusto.auctionhouseserver.auction.exception.InvalidOfferException;
import com.lucianaugusto.auctionhouseserver.base.model.AbstractEntity;
import com.lucianaugusto.auctionhouseserver.base.validation.Validator;
import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction extends AbstractEntity {

    @Column(unique = true)
    private String code;
    private String name;

    @ManyToOne
    private AuctionHouseUser seller;
    private BigDecimal price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    @OneToMany
    private List<AuctionHouseUser> watchlist;

    @ManyToOne
    private AuctionHouseUser currentBidCaller;

    public Auction(String code, String name, AuctionHouseUser seller, BigDecimal price, LocalDateTime startDateTime, int duration, String description) {
        this.code = code;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = generateEndDateTime(startDateTime, duration);
        this.description = description;
    }

    public void addNewBid(AuctionHouseUser bidCaller, BigDecimal value) {
        if (isClosed()) {
            throw new AuctionClosedException();
        }

        validateBidder(bidCaller);

        if (!validateAmount(value)) {
            throw new InvalidOfferException();
        }

        this.price = value;
        this.currentBidCaller = bidCaller;
    }

    public boolean isOpen() {
        return LocalDateTime.now().isBefore(this.endDateTime);
    }

    public boolean isClosed() {
        return !isOpen();
    }

    private boolean validateAmount(BigDecimal newOffer) {
        return newOffer.compareTo(this.price) > 0;
    }

    private void validateBidder(AuctionHouseUser bidder) {
        Validator.assertNotNull(bidder, AuctionErrorEnum.BIDDER_INVALID);
        Validator.assertNotEquals(bidder, this.seller, AuctionErrorEnum.BIDDER_INVALID);
    }

    private LocalDateTime generateEndDateTime(LocalDateTime startDateTime, int duration) {
        return startDateTime.plusSeconds(duration);
    }
}
