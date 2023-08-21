package com.hackathon.giftCardPersonalisation.exception;

public class GiftCardPersonalisationCustomException extends Exception {
    private String message;

    public GiftCardPersonalisationCustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
