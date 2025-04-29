package com.challenge.price.infrastructure.input.adapter.rest.command.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {

  Long productId;
  Long brandId;
  Integer priceList;
  LocalDateTime startDate;
  LocalDateTime endDate;
  BigDecimal price;
}
