package com.demo.prices.adapters.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    static List<Object[]> testData() {
        return Arrays.asList(
                new Object[]{"2020-06-14T10:00:00", 35455, 1, 35.50},
                new Object[]{"2020-06-14T16:00:00", 35455, 2, 25.45},
                new Object[]{"2020-06-14T21:00:00", 35455, 1, 35.50},
                new Object[]{"2020-06-15T10:00:00", 35455, 3, 30.50},
                new Object[]{"2020-06-16T21:00:00", 35455, 4, 38.95}
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void whenGetPrice_ShouldReturnCorrectResponse(String applicationDate, int productId, int priceList, double finalPrice) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(priceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(finalPrice));
    }

    @Test
    void whenMissingApplicationDate_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenMissingProductId_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenMissingBrandId_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenWrongDateFormat_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "invalid-date-format")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenWrongIdFormat_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "invalid-id-format")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenNoResults_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2021-01-01T00:00:00")
                        .param("productId", "99999")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}