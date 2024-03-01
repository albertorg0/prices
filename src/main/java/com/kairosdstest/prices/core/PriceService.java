package com.kairosdstest.prices.core;

import java.util.Date;

/**
 * Service interface for managing prices.
 */
public interface PriceService {

    /**
     * Find price based on brand, product, and application date.
     *
     * @param brandId         The brand identifier.
     * @param productId       The product identifier.
     * @param applicationDate The date of application.
     * @return A list of prices matching the criteria.
     */
    Price getPrice(Long brandId, Long productId, Date applicationDate);
}
