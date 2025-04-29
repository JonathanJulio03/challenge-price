package com.challenge.price.application.input;

import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;

public interface PriceUseCase {
  PriceModel getPrice(LocalDateTime date, Long productId, Long brandId);
}
