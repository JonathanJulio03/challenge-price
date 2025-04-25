package com.challenge.price.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceModel {
  Long id;
  Long brandId;
  Long productId;
  LocalDateTime startDate;
  LocalDateTime endDate;
  Integer priority;
  Integer priceList;
  BigDecimal price;
  String curr;
}
