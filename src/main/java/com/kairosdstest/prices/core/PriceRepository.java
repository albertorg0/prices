package com.kairosdstest.prices.core;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Custom repository definition for managing {@link Price} entities.
 */
@RepositoryDefinition(domainClass = Price.class, idClass = Long.class)
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
    Price findPriceByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
