package com.challenge.price.application.output;

import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;

public interface PricePort {
  PriceModel getPrice(LocalDateTime date, Long productId, Long brandId);
}
