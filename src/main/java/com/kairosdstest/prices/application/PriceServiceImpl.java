package com.kairosdstest.prices.application;


import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.ReadPricePort;
import com.kairosdstest.prices.core.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation of {@link PriceService}.
 */
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final ReadPricePort readPricePort;

    @Override
    public Price getPrice(Long brandId, Long productId, Date applicationDate) {

        return readPricePort
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate);
    }
}
