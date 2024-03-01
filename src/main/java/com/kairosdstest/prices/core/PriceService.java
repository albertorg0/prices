package com.kairosdstest.prices.core;

import java.util.Date;
import java.util.List;


/**
 * Service interface for managing prices.
 */
public interface PriceService {
    /**
     * Find prices based on brand, product, and application date.
     *
     * @param brandId         The brand identifier.
     * @param productId       The product identifier.
     * @param applicationDate The date of application.
     * @return A list of prices matching the criteria.
     */
    List<Price> findPrices(Long brandId, Long productId, Date applicationDate);
}
