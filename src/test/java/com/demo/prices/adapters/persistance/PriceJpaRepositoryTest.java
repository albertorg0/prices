package com.demo.prices.adapters.persistance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PriceJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    void whenFindFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc_ShouldReturnMatchingPrice() {
        // Given
        PriceEntity price = new PriceEntity();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setStartDate(new Date(System.currentTimeMillis() - 1000));
        price.setEndDate(new Date(System.currentTimeMillis() + 1000));
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(35.50));
        entityManager.persist(price);
        entityManager.flush();

        // When
        List<PriceEntity> foundPrices = priceJpaRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                1L,
                35455L,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );

        // Then
        assertFalse(foundPrices.isEmpty());
        assertEquals(price, foundPrices.get(0));
    }

    @Test
    void whenFindFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc_ShouldReturnNullIfNotFound() {
        // When
        List<PriceEntity> foundPrices = priceJpaRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                1L,
                35455L,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );

        // Then
        assertTrue(foundPrices.isEmpty());
    }
}