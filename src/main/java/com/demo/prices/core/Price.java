package com.demo.prices.core;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents the pricing information for a product within a specific timeframe.
 */

@Getter
@Setter
public class Price {

    private Long id;
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private Date lastUpdate;
    private String lastUpdateBy;
}