package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReadPriceAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private ReadPriceAdapter readPriceAdapter;

    @Test
    void whenPriceEntityNotFound_ShouldThrowNoResultsException() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        when(priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate)))
                .thenReturn(Optional.empty());

        // When
        assertThrows(NoResultsException.class, () ->
                readPriceAdapter.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate));

        // Then
        verify(priceJpaRepository, times(1))
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
    }

    @Test
    void whenPriceEntityFound_ShouldReturnPrice() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        PriceEntity expectedPrice = new PriceEntity();
        expectedPrice.setId(1L);
        when(priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                anyLong(), anyLong(), any(), any())).thenReturn(Optional.of(expectedPrice));

        // When
        Price result = readPriceAdapter
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate);

        // Then
        verify(priceJpaRepository, times(1))
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(expectedPrice.getId(), result.getId());
    }
}