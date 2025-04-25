package com.challenge.price.application.input;

import com.challenge.price.server.models.PriceDto;
import java.time.LocalDateTime;

public interface PriceUseCase {
  PriceDto getPrice(LocalDateTime date, Long productId, Long brandId);
}
