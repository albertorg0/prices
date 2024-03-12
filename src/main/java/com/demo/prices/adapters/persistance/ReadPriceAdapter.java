package com.demo.prices.adapters.persistance;

import com.demo.prices.core.Price;
import com.demo.prices.core.ReadPricePort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ReadPriceAdapter implements ReadPricePort {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Stream<Price> findPrices(
            Long brandId, Long productId, Date startDate, Date endDate) {
        ModelMapper modelMapper = new ModelMapper();
        return priceJpaRepository
                .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                        brandId, productId, startDate, endDate)
                .stream()
                .map(priceEntity -> modelMapper.map(priceEntity, Price.class));
    }
}