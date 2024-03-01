package com.kairosdstest.prices.adapters.rest;

import com.kairosdstest.prices.core.Price;
import com.kairosdstest.prices.core.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Controller class for the price endpoint.
 */
@RestController
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;


    /**
     * Retrieves pricing information based on the provided parameters.
     *
     * @param applicationDate The date and time of the pricing application.
     * @param productId       The identifier code of the product.
     * @param brandId         The foreign key of the brand or chain.
     * @return ResponseEntity containing the pricing information if found, or 404 if not found.
     */
    @GetMapping("/prices")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        Price price = priceService.getPrice(brandId, productId, applicationDate);
        if (price != null) {
            PriceResponse response = mapToResponseDto(price);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mapping method using the builder
    private PriceResponse mapToResponseDto(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .finalPrice(price.getPrice().doubleValue())
                .build();
    }
}
