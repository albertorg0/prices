package com.kairosdstest.prices.adapters.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for representing pricing information in the response.
 */
@Getter
@Setter
@NoArgsConstructor
public class PriceResponse {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
}
