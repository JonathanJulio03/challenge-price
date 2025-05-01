package com.challenge.price.infrastructure.input.adapter.rest.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  private static final String ENDPOINT = "/price";
  private static final String PRODUCT_ID = "35455";
  private static final String BRAND_ID = "1";


  private void assertPriceAtDate(String date, BigDecimal expectedPrice) throws Exception {
    mockMvc.perform(get(ENDPOINT)
            .param("date", date)
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(expectedPrice));
  }

  @Test
  @DisplayName("Should return price 35.50 for product 35455 and brand 1 on 2020-06-14 at 10:00")
  void shouldReturnPrice3550ForJune14At10() throws Exception {
    assertPriceAtDate("2020-06-14T10:00:00", BigDecimal.valueOf(35.50));
  }

  @Test
  @DisplayName("Should return price 25.45 for product 35455 and brand 1 on 2020-06-14 at 16:00")
  void shouldReturnPrice2545ForJune14At16() throws Exception {
    assertPriceAtDate("2020-06-14T16:00:00", BigDecimal.valueOf(25.45));
  }

  @Test
  @DisplayName("Should return price 35.50 for product 35455 and brand 1 on 2020-06-14 at 21:00")
  void shouldReturnPrice3550ForJune14At21() throws Exception {
    assertPriceAtDate("2020-06-14T21:00:00", BigDecimal.valueOf(35.50));
  }

  @Test
  @DisplayName("Should return price 30.50 for product 35455 and brand 1 on 2020-06-15 at 10:00")
  void shouldReturnPrice3050ForJune15At10() throws Exception {
    assertPriceAtDate("2020-06-15T10:00:00", BigDecimal.valueOf(30.50));
  }

  @Test
  @DisplayName("Should return price 38.95 for product 35455 and brand 1 on 2020-06-16 at 21:00")
  void shouldReturnPrice3895ForJune16At21() throws Exception {
    assertPriceAtDate("2020-06-16T21:00:00", BigDecimal.valueOf(38.95));
  }
}
