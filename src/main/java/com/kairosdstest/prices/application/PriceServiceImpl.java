package com.kairosdstest.prices.application;


import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.adapters.persistance.CustomPriceRepository;
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

    private final CustomPriceRepository priceRepository;

    @Override
    public Price getPrice(Long brandId, Long productId, Date applicationDate) {
        return priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);
    }
}
