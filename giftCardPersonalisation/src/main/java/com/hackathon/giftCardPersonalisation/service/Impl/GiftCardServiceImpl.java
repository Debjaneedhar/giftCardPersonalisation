package com.hackathon.giftCardPersonalisation.service.Impl;

import com.hackathon.giftCardPersonalisation.client.GiftCardPersonalisationClient;
import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceRequest;
import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceResponse;
import com.hackathon.giftCardPersonalisation.service.GiftCardService;
import com.hackathon.giftCardPersonalisation.util.GiftCardPersonalisationConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Log4j2
public class GiftCardServiceImpl implements GiftCardService {
    @Autowired
    private GiftCardPersonalisationClient giftCardPersonalisationClient;

    @Override
    public GiftCardBalanceResponse getBalance(GiftCardBalanceRequest giftCardBalanceRequest){
        Optional<GiftCardBalanceResponse> giftCardBalanceResponse =
                giftCardPersonalisationClient.getGiftCardBalanceResponse(GiftCardPersonalisationConstants.INVENTORY_STORE_SEARCH, giftCardBalanceRequest);
        return null;
    }
}