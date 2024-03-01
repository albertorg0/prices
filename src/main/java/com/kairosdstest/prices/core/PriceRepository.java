package com.kairosdstest.prices.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Custom repository definition for managing {@link Price} entities.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Finds a {@link Price} based on the given parameters.
     *
     * @param productId the identifier of the product
     * @param brandId   the identifier of the brand
     * @param startDate the start date for the price
     * @param endDate   the end date for the price
     * @return the matching {@link Price} entity or {@code null} if not found
     */
    List<Price> findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
