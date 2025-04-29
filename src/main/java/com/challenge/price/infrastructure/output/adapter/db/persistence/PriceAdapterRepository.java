package com.challenge.price.infrastructure.output.adapter.db.persistence;

import com.challenge.price.application.output.PricePort;
import com.challenge.price.commons.exception.TechnicalException;
import com.challenge.price.commons.exception.message.TechnicalErrorMessage;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.output.adapter.db.mapper.PriceDataMapper;
import com.challenge.price.infrastructure.output.adapter.db.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceAdapterRepository implements PricePort {

  private final PriceRepository repository;

  private final PriceDataMapper mapper;

  @Override
  public List<PriceModel> getPrices(LocalDateTime date, Long productId, Long brandId) {
    try {
      return repository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandDataId(
          date, date, productId, brandId).stream().map(mapper::toDomain).toList();
    } catch (Exception e) {
      throw new TechnicalException(e, TechnicalErrorMessage.PRICE_APPLY_EXCEPTION);
    }
  }
}
