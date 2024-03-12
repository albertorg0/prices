package com.demo.prices.adapters.rest;

import com.demo.prices.core.PriceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        ModelMapper modelMapper = new ModelMapper();
        PriceResponse response = modelMapper
                .map(priceService.getPrice(brandId, productId, applicationDate), PriceResponse.class);
        return ResponseEntity.ok(response);
    }
}