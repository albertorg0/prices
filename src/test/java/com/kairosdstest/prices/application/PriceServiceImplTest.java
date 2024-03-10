package com.kairosdstest.prices.application;

import com.kairosdstest.prices.adapters.persistance.PriceEntity;
import com.kairosdstest.prices.adapters.persistance.PriceJpaRepository;
import com.kairosdstest.prices.core.NoResultsException;
import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.ReadPricePort;
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
    private ReadPricePort readPricePort;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void getPrice_ShouldReturnResultFromRepository() {
        // Given
        Long brandId = 1L;
        Long productId = 35455L;
        Date applicationDate = new Date();
        Price expectedPrice = new Price();
        when(readPricePort.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                anyLong(), anyLong(), any(), any())).thenReturn(expectedPrice);

        // When
        Price result = priceService.getPrice(brandId, productId, applicationDate);

        // Then
        verify(readPricePort, times(1))
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        eq(brandId), eq(productId), eq(applicationDate), eq(applicationDate));
        assertEquals(expectedPrice, result);
    }
}
