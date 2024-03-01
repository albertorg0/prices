package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Custom repository definition for managing {@link Price} entities.
 */
@Repository
public interface CustomPriceRepository extends JpaRepository<Price, Long> {

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
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
