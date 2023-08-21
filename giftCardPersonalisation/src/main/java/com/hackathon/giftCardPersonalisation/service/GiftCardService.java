package com.hackathon.giftCardPersonalisation.service;

import com.hackathon.giftCardPersonalisation.model.*;

public interface GiftCardService {
    GiftCardBalanceResponse getBalance (GiftCardBalanceRequest giftCardBalanceRequest);
}
