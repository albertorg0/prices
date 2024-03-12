package com.demo.prices.adapters.rest;

import com.demo.prices.core.Price;
import com.demo.prices.core.PriceService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceControllerTest {

    private final PriceService priceService = mock(PriceService.class);
    private final PriceController priceController = new PriceController(priceService);

    @ParameterizedTest
    @MethodSource("testCases")
    void getPrice_ReturnsCorrectResponse(Date applicationDate, Long productId, Long brandId,
                                         Long expectedProductId, Long expectedBrandId, Integer expectedPriceList,
                                         Date expectedStartDate, Date expectedEndDate, Double expectedFinalPrice) {
        // Mock the service to return a Price object
        Price mockedPrice = createMockedPrice(expectedProductId, expectedBrandId, expectedPriceList,
                expectedStartDate, expectedEndDate, expectedFinalPrice);
        when(priceService.getPrice(brandId, productId, applicationDate)).thenReturn(mockedPrice);

        // Call the controller method
        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(applicationDate, productId, brandId);

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the content of the response
        PriceResponse response = responseEntity.getBody();
        assert response != null;
        assertEquals(expectedProductId, response.getProductId());
        assertEquals(expectedBrandId, response.getBrandId());
        assertEquals(expectedPriceList, response.getPriceList());
        assertEquals(expectedStartDate, response.getStartDate());
        assertEquals(expectedEndDate, response.getEndDate());
        assertEquals(BigDecimal.valueOf(expectedFinalPrice), response.getPrice());
    }

    // Helper method to create a mocked Price object
    private Price createMockedPrice(Long productId, Long brandId, Integer priceList,
                                    Date startDate, Date endDate, Double finalPrice) {
        Price mockedPrice = new Price();
        mockedPrice.setProductId(productId);
        mockedPrice.setBrandId(brandId);
        mockedPrice.setPriceList(priceList);
        mockedPrice.setStartDate(startDate);
        mockedPrice.setEndDate(endDate);
        mockedPrice.setPrice(BigDecimal.valueOf(finalPrice));
        return mockedPrice;
    }

    // Helper method to provide test cases
    private static Stream<Arguments> testCases() {
        return Stream.of(
                // Test 1
                Arguments.of(
                        createDate("2020-06-14T10:00:00"), 35455L, 1L,
                        35455L, 1L, 1, createDate("2020-06-14T00:00:00"),
                        createDate("2020-12-31T23:59:59"), 35.50),
                // Test 2
                Arguments.of(
                        createDate("2020-06-14T16:00:00"), 35455L, 1L,
                        35455L, 1L, 2, createDate("2020-06-14T15:00:00"),
                        createDate("2020-06-14T18:30:00"), 25.45),
                // Test 3
                Arguments.of(
                        createDate("2020-06-14T21:00:00"), 35455L, 1L,
                        35455L, 1L, 3, createDate("2020-06-15T00:00:00"),
                        createDate("2020-06-15T11:00:00"), 30.50),
                // Test 4
                Arguments.of(
                        createDate("2020-06-15T10:00:00"), 35455L, 1L,
                        35455L, 1L, 4, createDate("2020-06-15T16:00:00"),
                        createDate("2020-12-31T23:59:59"), 38.95),
                // Test 5
                Arguments.of(
                        createDate("2020-06-16T21:00:00"), 35455L, 1L,
                        35455L, 1L, 4, createDate("2020-06-15T16:00:00"),
                        createDate("2020-12-31T23:59:59"), 38.95)
        );
    }

    // Helper method to create Date objects
    private static Date createDate(String dateString) {
        return Date.from(java.time.LocalDateTime.parse(dateString).atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}