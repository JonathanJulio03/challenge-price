package com.challenge.price.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.price.application.output.PricePort;
import com.challenge.price.application.service.mapper.PriceMapper;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.server.models.PriceDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  private static final long PRODUCT_ID = 1L;
  private static final long BRAND_ID = 1L;
  private static final long LOW_PRIORITY_ID = 10L;
  private static final long HIGH_PRIORITY_ID = 20L;
  private static final long SINGLE_PRICE_ID = 30L;
  private static final BigDecimal LOW_PRICE = BigDecimal.valueOf(50.00);
  private static final BigDecimal HIGH_PRICE = BigDecimal.valueOf(99.99);
  private static final BigDecimal SINGLE_PRICE = BigDecimal.valueOf(79.99);

  private LocalDateTime currentDate;

  @InjectMocks
  private PriceService priceService;

  @Mock
  private PricePort pricePort;

  @Spy
  private PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

  @BeforeEach
  void setUp() {
    currentDate = LocalDateTime.now();
  }

  @Test
  @DisplayName("Should return PriceDto with highest priority when multiple prices exist")
  void shouldReturnPriceDtoWithHighestPriority() {
    PriceModel lowPriority = PriceModel.builder()
        .id(LOW_PRIORITY_ID)
        .price(LOW_PRICE)
        .priority(1)
        .build();

    PriceModel highPriority = PriceModel.builder()
        .id(HIGH_PRIORITY_ID)
        .price(HIGH_PRICE)
        .priority(5)
        .build();

    when(pricePort.getPrices(currentDate, PRODUCT_ID, BRAND_ID))
        .thenReturn(List.of(lowPriority, highPriority));

    PriceDto result = priceService.getPrice(currentDate, PRODUCT_ID, BRAND_ID);

    assertNotNull(result);
    verify(pricePort).getPrices(currentDate, PRODUCT_ID, BRAND_ID);
  }

  @Test
  @DisplayName("Should throw BusinessException when price list is empty")
  void shouldThrowBusinessExceptionWhenPriceListIsEmpty() {
    when(pricePort.getPrices(currentDate, PRODUCT_ID, BRAND_ID))
        .thenReturn(Collections.emptyList());

    assertThrows(BusinessException.class, () -> priceService.getPrice(currentDate, PRODUCT_ID, BRAND_ID));
    verify(pricePort).getPrices(currentDate, PRODUCT_ID, BRAND_ID);
  }

  @Test
  @DisplayName("Should return PriceDto when only one price exists")
  void shouldReturnPriceDtoWhenOnlyOnePriceExists() {
    PriceModel singlePriceModel = PriceModel.builder()
        .id(SINGLE_PRICE_ID)
        .price(SINGLE_PRICE)
        .priority(2)
        .build();

    when(pricePort.getPrices(currentDate, PRODUCT_ID, BRAND_ID))
        .thenReturn(List.of(singlePriceModel));

    PriceDto result = priceService.getPrice(currentDate, PRODUCT_ID, BRAND_ID);

    assertNotNull(result);
    verify(pricePort).getPrices(currentDate, PRODUCT_ID, BRAND_ID);
  }
}
