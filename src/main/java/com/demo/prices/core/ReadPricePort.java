package com.demo.prices.core;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Repository definition for managing {@link Price} entities.
 */
public interface ReadPricePort {

    Stream<Price> findPrices(
            Long brandId, Long productId, Date startDate, Date endDate);
}