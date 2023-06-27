package com.lucianaugusto.auctionhouseserver.user.repository;

import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuctionHouseUser, Long> {

    Optional<AuctionHouseUser> findByName(String userName);
}
