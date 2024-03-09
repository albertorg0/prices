package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.PriceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * JPA repository implementation for managing {@link Price} entities.
 */
@Repository
public interface CustomPriceRepository extends PriceRepository, JpaRepository<Price, Long> {


    @Override
    Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
