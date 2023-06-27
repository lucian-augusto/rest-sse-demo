package com.lucianaugusto.auctionhouseserver.auction.repository;

import com.lucianaugusto.auctionhouseserver.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findByEndDateTimeGreaterThan(LocalDateTime currentDateTime);

    Optional<Auction> findByCode(String auctionCode);
}
