package com.challenge.price.infrastructure.input.adapter.rest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.challenge.price.infrastructure.input.adapter.rest.command.response.PriceDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class PriceControllerSqlTest {

  @Autowired
  private PriceController controller;

  public static final long BRAND_ID = 1;
  public static final long PRODUCT_ID = 35455;


  private void validate(LocalDateTime date, BigDecimal price) {
    ResponseEntity<PriceDto> response = controller.getPriceApply(date, PRODUCT_ID, BRAND_ID);
    assertNotNull(response);
    assertEquals(0, price.compareTo(response.getBody().getPrice()));
    assertEquals(PRODUCT_ID, response.getBody().getProductId());
    assertEquals(BRAND_ID, response.getBody().getBrandId());
  }

  @Test
  @DisplayName("Test method return PriceDto 10:00 day 14 and Product 35455 and Brand 1")
  void testReturnPriceDtoHour10Day14() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
    validate(date, BigDecimal.valueOf(35.5));
  }

  @Test
  @DisplayName("Test method return PriceDto 16:00 day 14 and Product 35455 and Brand 1")
  void testReturnPriceDtoHour16Day14() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
    validate(date, BigDecimal.valueOf(25.45));
  }

  @Test
  @DisplayName("Test method return PriceDto 21:00 day 14 and Product 35455 and Brand 1")
  void testReturnPriceDtoHour21Day14() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
    validate(date, BigDecimal.valueOf(35.5));
  }

  @Test
  @DisplayName("Test method return PriceDto 10:00 day 15 and Product 35455 and Brand 1")
  void testReturnPriceDtoHour10Day15() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
    validate(date, BigDecimal.valueOf(30.5));
  }

  @Test
  @DisplayName("Test method return PriceDto 21:00 day 16 and Product 35455 and Brand 1")
  void testReturnPriceDtoHour21Day16() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
    validate(date, BigDecimal.valueOf(38.95));
  }
}