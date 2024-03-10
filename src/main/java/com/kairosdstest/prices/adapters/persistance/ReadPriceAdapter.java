package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.ReadPricePort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ReadPriceAdapter implements ReadPricePort {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Price findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, Date startDate, Date endDate) {
        ModelMapper modelMapper = new ModelMapper();
        return priceJpaRepository
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, startDate, endDate)
                .map(priceEntity -> modelMapper.map(priceEntity, Price.class))
                .orElseThrow(() -> new NoResultsException(
                        "No results found for brandId: "
                                + brandId + ", productId: "
                                + productId + " and date: "
                                + startDate.toString()));
    }
}