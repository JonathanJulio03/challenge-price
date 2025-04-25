package com.challenge.price.infrastructure.input.adapter.rest.resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @Test
  @DisplayName("Test method GetPriceApply")
  void testGetPriceApplyReturnsPriceDto() throws Exception {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    mockMvc.perform(get("/price")
            .param("date", date.format(formatter))
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.productId").value(productId))
        .andExpect(jsonPath("$.brandId").value(brandId));
  }
}
