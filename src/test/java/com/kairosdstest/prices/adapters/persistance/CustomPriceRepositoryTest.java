package com.kairosdstest.prices.adapters.persistance;

import com.kairosdstest.prices.core.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class CustomPriceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomPriceRepository customPriceRepository;

    @Test
    void whenFindFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc_ShouldReturnMatchingPrice() {
        // Given
        Price price = new Price();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setStartDate(new Date(System.currentTimeMillis() - 1000));
        price.setEndDate(new Date(System.currentTimeMillis() + 1000));
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(35.50));
        entityManager.persist(price);
        entityManager.flush();

        // When
        Price foundPrice = customPriceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                1L,
                35455L,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );

        // Then
        assertEquals(price, foundPrice);
    }

    @Test
    void whenFindFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc_ShouldReturnNullIfNotFound() {
        // When
        Price foundPrice = customPriceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                1L,
                35455L,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );

        // Then
        assertNull(foundPrice);
    }
}
