package com.kairosdstest.prices.application;


import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.PriceRepository;
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

    private final PriceRepository priceRepository;

    @Override
    public Price getPrice(Long brandId, Long productId, Date applicationDate) {
        Price price = priceRepository
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate);

        if (price == null) {
            throw new NoResultsException(
                    "No results found for brandId: "
                            + brandId + ", productId: "
                            + productId + " and applicationDate: "
                            + applicationDate.toString());
        }

        return price;
    }
}
