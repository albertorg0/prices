package com.kairosdstest.prices.application;


import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.PriceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link PriceService}.
 */
@Service
public class PriceServiceImpl implements PriceService {
    @Override
    public List<Price> findPrices(Long brandId, Long productId, Date applicationDate) {
        // TODO
        return null;
    }
}
