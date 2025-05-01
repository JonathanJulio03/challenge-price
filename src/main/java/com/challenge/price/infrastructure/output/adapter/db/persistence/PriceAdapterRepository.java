package com.challenge.price.infrastructure.output.adapter.db.persistence;

import com.challenge.price.application.output.PricePort;
import com.challenge.price.commons.exception.TechnicalException;
import com.challenge.price.commons.exception.message.TechnicalErrorMessage;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.output.adapter.db.mapper.PriceDataMapper;
import com.challenge.price.infrastructure.output.adapter.db.repository.PriceRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceAdapterRepository implements PricePort {

  private final PriceRepository repository;
  private final PriceDataMapper mapper;

  @Override
  public PriceModel getPrice(LocalDateTime date, Long productId, Long brandId) {
    return repository.findTopByDateAndProductIdAndBrandId(date, productId, brandId)
        .map(mapper::toDomain)
        .orElseThrow(() -> new TechnicalException(TechnicalErrorMessage.PRICE_APPLY_NOT_FOUND));
  }
}
