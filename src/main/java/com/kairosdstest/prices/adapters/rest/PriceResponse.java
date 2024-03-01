package com.kairosdstest.prices.adapters.rest;

import lombok.Builder;

import java.util.Date;

/**
 * DTO for representing pricing information in the response.
 */
@Builder
public class PriceResponse {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private Date startDate;
    private Date endDate;
    private Double finalPrice;
}