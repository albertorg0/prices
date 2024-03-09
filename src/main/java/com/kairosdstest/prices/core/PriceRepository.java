package com.kairosdstest.prices.core;

import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Repository definition for managing {@link Price} entities.
 */
public interface PriceRepository {

    /**
     * Finds a {@link Price} based on the given parameters.
     *
     * @param productId the identifier of the product
     * @param brandId   the identifier of the brand
     * @param startDate the start date for the price
     * @param endDate   the end date for the price
     * @return the matching {@link Price} entity or {@code null} if not found
     */
    Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, Date startDate, Date endDate);
}
