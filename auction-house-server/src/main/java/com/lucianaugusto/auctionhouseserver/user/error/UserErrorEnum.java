package com.lucianaugusto.auctionhouseserver.user.error;

import com.lucianaugusto.auctionhouseserver.base.error.ErrorEnumInterface;

public enum UserErrorEnum implements ErrorEnumInterface {

    USER_ALREADY_EXISTS("User already exists");

    private final String message;

    UserErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
