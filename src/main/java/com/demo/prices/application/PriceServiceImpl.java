package com.demo.prices.application;


import com.demo.prices.core.NoResultsException;
import com.demo.prices.core.Price;
import com.demo.prices.core.ReadPricePort;
import com.demo.prices.core.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

        // Get the price with the highest priority
        return readPricePort
                .findPrices(brandId, productId, applicationDate, applicationDate)
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(
                        () -> new NoResultsException(
                                "Price for brandId:%s, productId:%s and applicationDate:%s not found."
                                        .formatted(brandId, productId, applicationDate.toString())));
    }
}