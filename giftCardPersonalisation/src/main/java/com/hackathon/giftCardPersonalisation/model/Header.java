package com.hackathon.giftCardPersonalisation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@EqualsAndHashCode
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header implements Serializable {

    @JsonProperty("MarketCode")
    private String marketCode;

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Brand")
    private String brand;

    @JsonProperty("Channel")
    private String channel;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("CorrelationId")
    private String correlationId;

    @JsonProperty("OrderNumber")
    private String orderNumber;

    @JsonProperty("StoreNumber")
    private String storeNumber;

    @JsonProperty("RegisterNumber")
    private String registerNumber;

}
