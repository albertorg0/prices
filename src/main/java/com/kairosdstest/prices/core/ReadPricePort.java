package com.kairosdstest.prices.core;

import java.util.Date;

/**
 * Repository definition for managing {@link Price} entities.
 */
public interface ReadPricePort {

    Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, Date startDate, Date endDate);
}