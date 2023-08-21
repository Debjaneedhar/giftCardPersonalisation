package com.hackathon.giftCardPersonalisation.controller;


import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceRequest;
import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.giftCardPersonalisation.service.GiftCardService;


@RestController
public class GiftCardPersonalisationController {

    @Autowired
    private GiftCardService giftCardService;


    @PostMapping(value = "/getGCBalance", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCardBalanceResponse> getBalance(
            @RequestBody GiftCardBalanceRequest giftCardBalanceRequest) {
        return ResponseEntity.ok().body(giftCardService.getBalance(giftCardBalanceRequest));
    }
}
