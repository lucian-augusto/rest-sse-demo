package com.lucianaugusto.auctionhouseserver.auction.controller;

import com.lucianaugusto.auctionhouseserver.auction.http.request.NewAuctionRequest;
import com.lucianaugusto.auctionhouseserver.auction.http.request.NewBidRequest;
import com.lucianaugusto.auctionhouseserver.auction.http.response.AuctionResponse;
import com.lucianaugusto.auctionhouseserver.auction.model.Auction;
import com.lucianaugusto.auctionhouseserver.auction.service.AuctionService;
import com.lucianaugusto.auctionhouseserver.base.controller.AbstractController;
import com.lucianaugusto.auctionhouseserver.base.error.BusinessRuleException;
import com.lucianaugusto.auctionhouseserver.base.http.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auction")
@Slf4j
public class AuctionController extends AbstractController {

    private final AuctionService service;

    @Autowired
    public AuctionController(AuctionService service) {
        this.service = service;
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<?> createAuction(@RequestBody NewAuctionRequest request) {
        AuctionResponse response;
        try {
            Auction auction = service.createNewAuction(request);
            response = new AuctionResponse(auction);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new BaseErrorResponse(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("list")
    public ResponseEntity<?> listActiveAuctions() {
        List<Auction> auctionList = service.listAllOpenAuctions();
        List<AuctionResponse> auctionResponses = auctionList.stream().map(AuctionResponse::new).toList();
        return ResponseEntity.ok(auctionResponses);
    }

    @PostMapping("bid")
    @ResponseBody
    public ResponseEntity<?> makeNewBid(@RequestBody NewBidRequest newBid) {
        try {
            Auction auction = service.addNewBid(newBid);
            return ResponseEntity.ok(new AuctionResponse(auction));
        } catch (BusinessRuleException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
