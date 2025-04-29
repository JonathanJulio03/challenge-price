package com.challenge.price.application.output;

import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PricePort {
  Optional<PriceModel> getPrices(LocalDateTime date, Long productId, Long brandId);
}
