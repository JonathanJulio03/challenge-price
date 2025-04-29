package com.challenge.price.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.price.application.output.PricePort;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.domain.PriceModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  private static final long PRODUCT_ID = 1L;
  private static final long BRAND_ID = 1L;
  private static final long SINGLE_PRICE_ID = 30L;
  private static final BigDecimal SINGLE_PRICE = BigDecimal.valueOf(79.99);

  private LocalDateTime currentDate;

  @InjectMocks
  private PriceService priceService;

  @Mock
  private PricePort pricePort;

  @BeforeEach
  void setUp() {
    currentDate = LocalDateTime.now();
  }

  @Test
  @DisplayName("Should return PriceModel when only one price exists")
  void shouldReturnPriceDtoWhenOnlyOnePriceExists() {
    PriceModel singlePriceModel = PriceModel.builder()
        .id(SINGLE_PRICE_ID)
        .price(SINGLE_PRICE)
        .priority(2)
        .build();

    when(pricePort.getPrices(currentDate, PRODUCT_ID, BRAND_ID))
        .thenReturn(List.of(singlePriceModel));

    PriceModel result = priceService.getPrice(currentDate, PRODUCT_ID, BRAND_ID);

    assertNotNull(result);
    assertEquals(SINGLE_PRICE, result.getPrice());
  }

  @Test
  @DisplayName("Should throw BusinessException when price list is empty")
  void shouldThrowBusinessExceptionWhenPriceListIsEmpty() {
    when(pricePort.getPrices(currentDate, PRODUCT_ID, BRAND_ID))
        .thenReturn(new ArrayList<>());

    assertThrows(BusinessException.class,
        () -> priceService.getPrice(currentDate, PRODUCT_ID, BRAND_ID));
    verify(pricePort).getPrices(currentDate, PRODUCT_ID, BRAND_ID);
  }
}