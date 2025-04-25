package com.challenge.price.application.output;

import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;
import java.util.List;

public interface PricePort {
  List<PriceModel> getPrices(LocalDateTime date, Long productId, Long brandId);
}
