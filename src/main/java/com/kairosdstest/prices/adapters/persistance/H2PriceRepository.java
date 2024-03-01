package com.kairosdstest.prices.adapters.persistance;


import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.PriceRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * Custom implementation of {@link PriceRepository}.
 */
@Repository
public abstract class H2PriceRepository implements PriceRepository {
    @Override
    public List<Price> findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            Long productId, Long brandId, Date startDate, Date endDate) {
        // TODO
        return null;
    }

    @Override
    public Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long productId, Long brandId, Date startDate, Date endDate) {
        // TODO
        return null;
    }
}
