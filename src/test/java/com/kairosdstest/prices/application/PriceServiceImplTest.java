package com.kairosdstest.prices.application;

import com.kairosdstest.prices.adapters.persistance.CustomPriceRepository;
import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PriceServiceImplTest {

    @Mock
    private CustomPriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void getPrice_ShouldThrowNoResultsException() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate)))
                .thenReturn(null);

        // When
        assertThrows(NoResultsException.class, () ->
                priceService.getPrice(brandId, productId, applicationDate));

        // Then
        verify(priceRepository, times(1))
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
    }

    @Test
    void getPrice_ShouldReturnResultFromRepository() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        Price expectedPrice = new Price();
        when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                anyLong(), anyLong(), any(), any())).thenReturn(expectedPrice);

        // When
        Price result = priceService.getPrice(brandId, productId, applicationDate);

        // Then
        verify(priceRepository, times(1))
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(expectedPrice, result);
    }
}
