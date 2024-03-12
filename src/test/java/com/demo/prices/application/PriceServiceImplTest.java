package com.demo.prices.application;

import com.demo.prices.core.NoResultsException;
import com.demo.prices.core.Price;
import com.demo.prices.core.ReadPricePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PriceServiceImplTest {

    @Mock
    private ReadPricePort readPricePort;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void whenPriceEntitiesNotFound_ShouldThrowNoResultsException() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        when(readPricePort.findPrices(
                eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate)))
                .thenReturn(Stream.<Price>builder().build());

        // When
        assertThrows(NoResultsException.class, () ->
                priceService.getPrice(brandId, productId, applicationDate));

        // Then
        verify(readPricePort, times(1))
                .findPrices(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
    }

    @Test
    void whenGetPriceSingleResult_ShouldReturnPrice() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        Price expectedPrice = new Price();
        when(readPricePort.findPrices(
                anyLong(), anyLong(), any(), any())).thenReturn(Stream.of(expectedPrice));

        // When
        Price result = priceService.getPrice(brandId, productId, applicationDate);

        // Then
        verify(readPricePort, times(1))
                .findPrices(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(expectedPrice, result);
    }

    @Test
    void whenGetPriceMultipleResults_ShouldReturnResultPrioritizedPrice() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        Price highPrioPrice = new Price();
        highPrioPrice.setPriority(1);
        Price lowPrioPrice = new Price();
        lowPrioPrice.setPriority(0);
        when(readPricePort.findPrices(
                anyLong(), anyLong(), any(), any())).thenReturn(Stream.of(highPrioPrice, lowPrioPrice));

        // When
        Price result = priceService.getPrice(brandId, productId, applicationDate);

        // Then
        verify(readPricePort, times(1))
                .findPrices(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(highPrioPrice, result);
    }
}