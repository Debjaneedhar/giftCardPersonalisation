package com.hackathon.giftCardPersonalisation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class GiftCardBalanceRequest {

    @JsonProperty("header")
    private Header header;

    @JsonProperty("giftCards")
    private GiftCards giftCards;
}
