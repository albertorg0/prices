package com.demo.prices.adapters.persistance;

import com.demo.prices.core.Price;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReadPriceAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private ReadPriceAdapter readPriceAdapter;

    @Test
    void whenPriceEntitiesFound_ShouldReturnPriceList() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        PriceEntity expectedPrice = new PriceEntity();
        expectedPrice.setId(1L);
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                anyLong(), anyLong(), any(), any())).thenReturn(List.of(expectedPrice));

        // When
        Stream<Price> result = readPriceAdapter
                .findPrices(
                        brandId, productId, applicationDate, applicationDate);

        // Then
        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(expectedPrice.getId(), result.toList().get(0).getId());
    }

    @Test
    void whenPriceEntitiesEmpty_ShouldReturnEmptyPriceList() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                anyLong(), anyLong(), any(), any())).thenReturn(Collections.emptyList());

        // When
        Stream<Price> result = readPriceAdapter
                .findPrices(
                        brandId, productId, applicationDate, applicationDate);

        // Then
        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertTrue(result.toList().isEmpty());
    }
}