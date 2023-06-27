package com.lucianaugusto.auctionhouseserver.user.controller;

import com.lucianaugusto.auctionhouseserver.base.controller.AbstractController;
import com.lucianaugusto.auctionhouseserver.user.http.request.NewUserRequest;
import com.lucianaugusto.auctionhouseserver.user.http.response.UserCreatedResponse;
import com.lucianaugusto.auctionhouseserver.user.model.AuctionHouseUser;
import com.lucianaugusto.auctionhouseserver.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends AbstractController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("new")
    @ResponseBody
    public ResponseEntity<?> createNewUser(@RequestBody NewUserRequest request) {
        try {
            AuctionHouseUser user = service.createNewUser(request);
            return ResponseEntity.ok(new UserCreatedResponse(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
