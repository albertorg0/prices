package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * JPA repository implementation for managing {@link Price} entities.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {


    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}